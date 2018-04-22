package org.thinking.logistics.services.core.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.support.InventoryState;
import org.thinking.logistics.services.core.entity.*;
import org.thinking.logistics.services.core.entity.inventory.Inventory;

import java.util.LinkedList;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByWarehouseAndOwnerAndGoodsAndBatchesAndLocationAndInventoryState(Warehouse warehouse, Owner owner, Goods goods, Batches batches, Location location, InventoryState inventoryState);

    @Query(value = "select i from Inventory i " +
        "where i.goods = :goods " +
        "and i.batches = :batches " +
        "and i.transitionalQuantity > 0 " +
        "order by i.transitionalQuantity")
    LinkedList<Inventory> acquireTransitionalInventory(Goods goods, Batches batches);

    @Query(value = "select i from Inventory i " +
        "where i.goods = :goods " +
        "and i.batches = :batches " +
        "and i.inventoryState = :inventoryState " +
        "and i.location.area.storeCategory like :storeCategory " +
        "and i.location.area.storeNo like :storeNo " +
        "and i.transitionalQuantity = 0 " +
        "order by case when i.location.area.storeCategory = 'LTK' then (select p.count from PilerCommand p) else 1 end," +
        "i.availableOutboundQuantity")
    LinkedList<Inventory> acquireLocationInventory(Goods goods, Batches batches, InventoryState inventoryState, String storeCategory, String storeNo);
}
package org.thinking.logistics.services.core.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.support.InventoryState;
import org.thinking.logistics.services.core.entity.*;
import org.thinking.logistics.services.core.entity.inventory.Inventory;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Inventory findByWarehouseAndOwnerAndGoodsAndBatchesAndLocationAndInventoryState(Warehouse warehouse, Owner owner, Goods goods, Batches batches, Location location, InventoryState inventoryState);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select i from Inventory i " +
        "where i.goods = :goods " +
        "and i.batches = :batches " +
        "and i.transitionalQuantity > 0 " +
        "order by i.transitionalQuantity")
    List<Inventory> acquireTransitionalInventory(Goods goods, Batches batches);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select i from Inventory i " +
        "where i.goods = :goods " +
        "and i.batches = :batches " +
        "and i.inventoryState = :inventoryState " +
        "and i.location.area.storeCategory like :storeCategory " +
        "and i.location.area.storeNo like :storeNo " +
        "and i.transitionalQuantity = 0 ")
//        "order by case when i.location.area.storeCategory = 'LTK' then (select count(p) from PilerCommand p) else 1 end," +
//        "i.availableOutboundQuantity")
    List<Inventory> acquireLocationInventory(Goods goods, Batches batches, InventoryState inventoryState, String storeCategory, String storeNo);
}
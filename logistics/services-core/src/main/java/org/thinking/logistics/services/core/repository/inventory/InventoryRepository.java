package org.thinking.logistics.services.core.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.entity.inventory.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
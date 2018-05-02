package org.thinking.logistics.services.core.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.inventory.BatchesInventory;

@Repository
public interface BatchesInventoryRepository extends JpaRepository<BatchesInventory, Long> {
}
package org.thinking.logistics.services.core.repository.inventory;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.inventory.BatchesInventory;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface BatchesInventoryRepository extends DomainRepository<BatchesInventory, Long> {
}
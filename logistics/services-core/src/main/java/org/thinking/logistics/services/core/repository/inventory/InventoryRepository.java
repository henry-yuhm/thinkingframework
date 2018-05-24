package org.thinking.logistics.services.core.repository.inventory;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.inventory.Inventory;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface InventoryRepository extends DomainRepository<Inventory, Long> {
}
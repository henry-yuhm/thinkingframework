package org.thinking.sce.service.core.repository.inventory;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.inventory.Inventory;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface InventoryRepository extends DomainRepository<Inventory, Long> {
}
package org.thinking.sce.service.core.repository.inventory;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.inventory.LotInventory;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface LotInventoryRepository extends DomainRepository<LotInventory, Long> {
}
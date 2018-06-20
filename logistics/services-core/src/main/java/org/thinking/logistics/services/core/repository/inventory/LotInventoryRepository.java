package org.thinking.logistics.services.core.repository.inventory;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.inventory.LotInventory;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface LotInventoryRepository extends DomainRepository<LotInventory, Long> {
}
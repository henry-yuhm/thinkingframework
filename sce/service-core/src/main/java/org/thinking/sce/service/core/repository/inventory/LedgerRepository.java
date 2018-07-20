package org.thinking.sce.service.core.repository.inventory;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.inventory.Ledger;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface LedgerRepository extends DomainRepository<Ledger, Long> {
}
package org.thinking.logistics.services.core.repository.inventory;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.inventory.Ledger;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface LedgerRepository<L extends Ledger> extends DomainRepository<L, Long> {
}
package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.ReplenishmentOrderHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReplenishmentOrderRepository extends DomainRepository<ReplenishmentOrderHeader, Long> {
}
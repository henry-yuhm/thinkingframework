package org.thinking.sce.service.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.document.ReplenishmentOrderHeader;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ReplenishmentOrderRepository extends DomainRepository<ReplenishmentOrderHeader, Long> {
}
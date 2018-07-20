package org.thinking.sce.service.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.document.PurchaseOrderHeader;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface PurchaseOrderRepository extends DomainRepository<PurchaseOrderHeader, Long> {
}
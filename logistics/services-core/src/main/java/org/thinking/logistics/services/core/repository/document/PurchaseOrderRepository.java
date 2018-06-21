package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.PurchaseOrderHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface PurchaseOrderRepository extends DomainRepository<PurchaseOrderHeader, Long> {
}
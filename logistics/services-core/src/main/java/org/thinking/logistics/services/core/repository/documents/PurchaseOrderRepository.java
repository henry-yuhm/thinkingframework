package org.thinking.logistics.services.core.repository.documents;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.documents.PurchaseOrderHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface PurchaseOrderRepository extends DomainRepository<PurchaseOrderHeader, Long> {
}
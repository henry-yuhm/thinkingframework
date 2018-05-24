package org.thinking.logistics.services.core.repository.documents;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.documents.InverseOrderDetail;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface InverseOrderRepository extends DomainRepository<InverseOrderDetail, Long> {
}
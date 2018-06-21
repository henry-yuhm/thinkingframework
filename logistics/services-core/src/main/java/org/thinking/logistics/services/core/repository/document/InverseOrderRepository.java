package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.InverseOrderDetail;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface InverseOrderRepository extends DomainRepository<InverseOrderDetail, Long> {
}
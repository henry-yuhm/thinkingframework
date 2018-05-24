package org.thinking.logistics.services.core.repository.documents;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.documents.InboundOrderHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface InboundOrderRepository extends DomainRepository<InboundOrderHeader, Long> {
}
package org.thinking.logistics.services.core.repository.documents;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface OutboundOrderRepository extends DomainRepository<OutboundOrderHeader, Long> {
}
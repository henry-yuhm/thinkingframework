package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.InboundOrderHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface InboundOrderRepository extends DomainRepository<InboundOrderHeader, Long> {
}
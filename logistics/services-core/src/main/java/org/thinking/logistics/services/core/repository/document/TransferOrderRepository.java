package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.TransferOrderHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface TransferOrderRepository extends DomainRepository<TransferOrderHeader, Long> {
}
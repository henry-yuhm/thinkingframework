package org.thinking.sce.service.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.document.TransferOrderHeader;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface TransferOrderRepository extends DomainRepository<TransferOrderHeader, Long> {
}
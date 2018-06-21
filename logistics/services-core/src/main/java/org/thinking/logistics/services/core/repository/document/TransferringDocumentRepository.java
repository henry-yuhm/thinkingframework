package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.TransferringDocumentHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface TransferringDocumentRepository extends DomainRepository<TransferringDocumentHeader, Long> {
}
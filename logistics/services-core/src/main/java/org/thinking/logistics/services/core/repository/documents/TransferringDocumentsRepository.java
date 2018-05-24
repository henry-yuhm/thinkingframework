package org.thinking.logistics.services.core.repository.documents;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.documents.TransferringDocumentsHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface TransferringDocumentsRepository extends DomainRepository<TransferringDocumentsHeader, Long> {
}
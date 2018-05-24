package org.thinking.logistics.services.core.repository.documents;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.documents.ReplenishingDocumentsHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReplenishingDocumentsRepository extends DomainRepository<ReplenishingDocumentsHeader, Long> {
}
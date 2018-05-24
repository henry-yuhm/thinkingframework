package org.thinking.logistics.services.core.repository.documents;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.documents.DistributionDocumentsHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface DistributionDocumentsRepository extends DomainRepository<DistributionDocumentsHeader, Long> {
}
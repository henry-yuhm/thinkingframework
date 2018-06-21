package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.DistributionDocumentHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface DistributionDocumentRepository extends DomainRepository<DistributionDocumentHeader, Long> {
}
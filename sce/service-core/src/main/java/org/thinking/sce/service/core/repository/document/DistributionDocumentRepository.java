package org.thinking.sce.service.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.document.DistributionDocumentHeader;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface DistributionDocumentRepository extends DomainRepository<DistributionDocumentHeader, Long> {
}
package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.ReplenishmentDocumentHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReplenishmentDocumentRepository extends DomainRepository<ReplenishmentDocumentHeader, Long> {
}
package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.InventoryDocumentHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface InventoryDocumentRepository extends DomainRepository<InventoryDocumentHeader, Long> {
}
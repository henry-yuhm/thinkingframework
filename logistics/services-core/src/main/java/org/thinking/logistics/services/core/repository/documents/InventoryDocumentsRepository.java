package org.thinking.logistics.services.core.repository.documents;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.documents.InventoryDocumentsHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface InventoryDocumentsRepository extends DomainRepository<InventoryDocumentsHeader, Long> {
}
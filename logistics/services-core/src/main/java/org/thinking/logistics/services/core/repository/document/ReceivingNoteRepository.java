package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.ReceivingNoteHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReceivingNoteRepository extends DomainRepository<ReceivingNoteHeader, Long> {
}
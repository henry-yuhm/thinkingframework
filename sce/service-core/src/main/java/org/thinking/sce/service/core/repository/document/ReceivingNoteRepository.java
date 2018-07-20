package org.thinking.sce.service.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.document.ReceivingNoteHeader;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ReceivingNoteRepository extends DomainRepository<ReceivingNoteHeader, Long> {
}
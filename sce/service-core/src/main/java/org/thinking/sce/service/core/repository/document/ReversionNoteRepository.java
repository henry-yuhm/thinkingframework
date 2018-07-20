package org.thinking.sce.service.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.document.ReversionNoteDetail;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ReversionNoteRepository extends DomainRepository<ReversionNoteDetail, Long> {
}
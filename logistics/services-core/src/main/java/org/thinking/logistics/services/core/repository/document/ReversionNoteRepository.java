package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.ReversionNoteDetail;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReversionNoteRepository extends DomainRepository<ReversionNoteDetail, Long> {
}
package org.thinking.sce.service.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.document.CheckListHeader;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface CheckListRepository extends DomainRepository<CheckListHeader, Long> {
}
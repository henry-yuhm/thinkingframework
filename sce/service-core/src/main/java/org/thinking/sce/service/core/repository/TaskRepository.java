package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.document.PickingList;

@Repository
public interface TaskRepository extends DomainRepository<PickingList, Long> {
}
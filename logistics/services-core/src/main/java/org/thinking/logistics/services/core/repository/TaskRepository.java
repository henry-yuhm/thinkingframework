package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.common.Task;

@Repository
public interface TaskRepository extends DomainRepository<Task, Long> {
}
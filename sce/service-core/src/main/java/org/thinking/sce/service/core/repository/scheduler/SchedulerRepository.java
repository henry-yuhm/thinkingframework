package org.thinking.sce.service.core.repository.scheduler;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.scheduler.Scheduler;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface SchedulerRepository extends DomainRepository<Scheduler, Long> {
}
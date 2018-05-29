package org.thinking.logistics.services.core.repository.scheduler;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.scheduler.Scheduler;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface SchedulerRepository extends DomainRepository<Scheduler, Long> {
}
package org.thinking.logistics.services.core.repository.stagingarea;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.stagingarea.Stagingarea;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface StagingareaRepository extends DomainRepository<Stagingarea, Long> {
}
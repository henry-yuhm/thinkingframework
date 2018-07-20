package org.thinking.sce.service.core.repository.stagingarea;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.stagingarea.Stagingarea;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface StagingareaRepository extends DomainRepository<Stagingarea, Long> {
}
package org.thinking.sce.service.core.repository.stagingarea;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.stagingarea.StagingareaConfiguration;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface StagingareaConfigurationRepository extends DomainRepository<StagingareaConfiguration, Long> {
}
package org.thinking.logistics.services.core.repository.stagingarea;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.stagingarea.StagingareaConfiguration;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface StagingareaConfigurationRepository extends DomainRepository<StagingareaConfiguration, Long> {
}
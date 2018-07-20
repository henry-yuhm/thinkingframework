package org.thinking.sce.service.core.repository.stagingarea;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.stagingarea.VirtualConfiguration;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface VirtualConfigurationRepository extends DomainRepository<VirtualConfiguration, Long> {
}
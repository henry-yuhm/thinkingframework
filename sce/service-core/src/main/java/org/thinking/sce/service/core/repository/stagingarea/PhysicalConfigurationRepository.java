package org.thinking.sce.service.core.repository.stagingarea;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.stagingarea.PhysicalConfiguration;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface PhysicalConfigurationRepository extends DomainRepository<PhysicalConfiguration, Long> {
}
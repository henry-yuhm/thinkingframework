package org.thinking.logistics.services.core.repository.stagingarea;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.stagingarea.PhysicalConfiguration;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface PhysicalConfigurationRepository extends DomainRepository<PhysicalConfiguration, Long> {
}
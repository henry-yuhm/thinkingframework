package org.thinking.logistics.services.core.repository.stagingarea;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.stagingarea.VirtualConfiguration;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface VirtualConfigurationRepository extends DomainRepository<VirtualConfiguration, Long> {
}
package org.thinking.sce.service.core.repository.inventory;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.inventory.OutboundConfiguration;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface OutboundConfigurationRepository extends DomainRepository<OutboundConfiguration, Long> {
}
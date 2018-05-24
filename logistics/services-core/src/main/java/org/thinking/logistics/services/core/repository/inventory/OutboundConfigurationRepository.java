package org.thinking.logistics.services.core.repository.inventory;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.inventory.OutboundConfiguration;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface OutboundConfigurationRepository extends DomainRepository<OutboundConfiguration, Long> {
}
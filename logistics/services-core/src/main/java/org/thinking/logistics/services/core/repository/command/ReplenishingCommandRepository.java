package org.thinking.logistics.services.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.command.ReplenishingCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReplenishingCommandRepository extends DomainRepository<ReplenishingCommand, Long> {
}
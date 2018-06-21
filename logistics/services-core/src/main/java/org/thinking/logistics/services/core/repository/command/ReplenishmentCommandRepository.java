package org.thinking.logistics.services.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.command.ReplenishmentCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReplenishmentCommandRepository extends DomainRepository<ReplenishmentCommand, Long> {
}
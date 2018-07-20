package org.thinking.sce.service.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.command.ReplenishmentCommand;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ReplenishmentCommandRepository extends DomainRepository<ReplenishmentCommand, Long> {
}
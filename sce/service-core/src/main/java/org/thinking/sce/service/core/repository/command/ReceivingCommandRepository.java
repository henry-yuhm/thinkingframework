package org.thinking.sce.service.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.command.ReceivingCommand;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ReceivingCommandRepository extends DomainRepository<ReceivingCommand, Long> {
}
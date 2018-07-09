package org.thinking.logistics.services.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.command.ReceivingCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReceivingCommandRepository extends DomainRepository<ReceivingCommand, Long> {
}
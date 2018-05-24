package org.thinking.logistics.services.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.command.OutboundCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface OutboundCommandRepository extends DomainRepository<OutboundCommand, Long> {
}
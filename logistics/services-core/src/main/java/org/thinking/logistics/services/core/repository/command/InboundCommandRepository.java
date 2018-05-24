package org.thinking.logistics.services.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.command.InboundCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface InboundCommandRepository extends DomainRepository<InboundCommand, Long> {
}
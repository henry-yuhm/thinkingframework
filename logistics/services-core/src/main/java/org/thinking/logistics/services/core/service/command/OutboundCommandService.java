package org.thinking.logistics.services.core.service.command;

import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.command.OutboundCommand;
import org.thinking.logistics.services.core.domain.command.QOutboundCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class OutboundCommandService extends DomainService<QOutboundCommand, OutboundCommand, Long> {
    public OutboundCommandService(EntityManager entityManager, DomainRepository<OutboundCommand, Long> repository) {
        super(entityManager, repository, QOutboundCommand.outboundCommand);
    }
}
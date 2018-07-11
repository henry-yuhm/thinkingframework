package org.thinking.logistics.services.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.command.TransferCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface TransferCommandRepository extends DomainRepository<TransferCommand, Long> {
}
package org.thinking.logistics.services.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.command.TransferringCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface TransferringCommandRepository extends DomainRepository<TransferringCommand, Long> {
}
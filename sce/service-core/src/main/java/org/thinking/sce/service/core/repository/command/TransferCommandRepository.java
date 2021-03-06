package org.thinking.sce.service.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.command.TransferCommand;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface TransferCommandRepository extends DomainRepository<TransferCommand, Long> {
}
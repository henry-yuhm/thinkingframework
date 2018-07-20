package org.thinking.sce.service.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.command.InventoryCommand;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface InventoryCommandRepository extends DomainRepository<InventoryCommand, Long> {
}
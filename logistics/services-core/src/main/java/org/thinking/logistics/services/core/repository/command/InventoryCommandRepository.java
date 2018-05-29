package org.thinking.logistics.services.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.command.InventoryCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface InventoryCommandRepository extends DomainRepository<InventoryCommand, Long> {
}
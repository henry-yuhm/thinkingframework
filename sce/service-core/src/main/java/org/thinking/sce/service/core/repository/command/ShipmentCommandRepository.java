package org.thinking.sce.service.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.command.ShipmentCommand;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ShipmentCommandRepository extends DomainRepository<ShipmentCommand, Long> {
}
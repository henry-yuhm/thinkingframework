package org.thinking.logistics.services.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.command.ShipmentCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ShipmentCommandRepository extends DomainRepository<ShipmentCommand, Long> {
}
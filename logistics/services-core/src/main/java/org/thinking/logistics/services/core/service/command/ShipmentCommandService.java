package org.thinking.logistics.services.core.service.command;

import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.command.QShipmentCommand;
import org.thinking.logistics.services.core.domain.command.ShipmentCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class ShipmentCommandService extends DomainService<QShipmentCommand, ShipmentCommand, Long> {
    public ShipmentCommandService(EntityManager entityManager, DomainRepository<ShipmentCommand, Long> repository) {
        super(entityManager, repository, QShipmentCommand.shipmentCommand);
    }
}
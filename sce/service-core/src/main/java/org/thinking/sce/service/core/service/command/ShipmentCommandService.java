package org.thinking.sce.service.core.service.command;

import org.springframework.stereotype.Service;
import org.thinking.sce.service.core.domain.command.ShipmentCommand;
import org.thinking.sce.service.core.repository.DomainRepository;
import org.thinking.sce.service.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class ShipmentCommandService extends DomainService<QShipmentCommand, ShipmentCommand, Long> {
    public ShipmentCommandService(EntityManager entityManager, DomainRepository<ShipmentCommand, Long> repository) {
        super(entityManager, repository, QShipmentCommand.shipmentCommand);
    }
}
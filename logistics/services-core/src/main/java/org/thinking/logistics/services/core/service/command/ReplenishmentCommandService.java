package org.thinking.logistics.services.core.service.command;

import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.command.QReplenishmentCommand;
import org.thinking.logistics.services.core.domain.command.ReplenishmentCommand;
import org.thinking.logistics.services.core.domain.core.Item;
import org.thinking.logistics.services.core.domain.core.Location;
import org.thinking.logistics.services.core.domain.core.Lot;
import org.thinking.logistics.services.core.domain.core.Warehouse;
import org.thinking.logistics.services.core.domain.support.CommandStage;
import org.thinking.logistics.services.core.domain.support.CommandType;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@Service
public class ReplenishmentCommandService extends DomainService<QReplenishmentCommand, ReplenishmentCommand, Long> {
    public ReplenishmentCommandService(EntityManager entityManager, DomainRepository<ReplenishmentCommand, Long> repository) {
        super(entityManager, repository, QReplenishmentCommand.replenishmentCommand);
    }

    public final List<ReplenishmentCommand> acquire(Warehouse warehouse, Item item, Lot lot, Location location) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().item.eq(item),
                this.getPath().lot.eq(lot),
                this.getPath().targetLocation.eq(location),
                this.getPath().commandType.eq(CommandType.REPLENISHMENT),
                this.getPath().stage.lt(CommandStage.TERMINATED),
                this.getPath().availableQuantity.gt(0))
            .orderBy(
                this.getPath().commandCategory.asc(),
                this.getPath().availableQuantity.desc())
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch();
    }
}
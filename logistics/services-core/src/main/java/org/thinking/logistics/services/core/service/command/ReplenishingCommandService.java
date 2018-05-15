package org.thinking.logistics.services.core.service.command;

import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.Batches;
import org.thinking.logistics.services.core.domain.Goods;
import org.thinking.logistics.services.core.domain.Location;
import org.thinking.logistics.services.core.domain.Warehouse;
import org.thinking.logistics.services.core.domain.command.QReplenishingCommand;
import org.thinking.logistics.services.core.domain.command.ReplenishingCommand;
import org.thinking.logistics.services.core.domain.support.CommandStage;
import org.thinking.logistics.services.core.domain.support.CommandType;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@Service
public class ReplenishingCommandService extends DomainService<QReplenishingCommand, ReplenishingCommand, Long> {
    public ReplenishingCommandService(EntityManager entityManager, DomainRepository<ReplenishingCommand, Long> repository) {
        super(entityManager, repository, QReplenishingCommand.replenishingCommand);
    }

    public final List<ReplenishingCommand> acquire(Warehouse warehouse, Goods goods, Batches batches, Location location) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().goods.eq(goods),
                this.getPath().batches.eq(batches),
                this.getPath().targetLocation.eq(location),
                this.getPath().commandType.eq(CommandType.REPLENISHING),
                this.getPath().stage.lt(CommandStage.TERMINATED),
                this.getPath().availableQuantity.gt(0))
            .orderBy(
                this.getPath().commandCategory.asc(),
                this.getPath().availableQuantity.desc())
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch();
    }
}
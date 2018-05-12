package org.thinking.logistics.services.core.repository.command;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Batches;
import org.thinking.logistics.services.core.domain.Goods;
import org.thinking.logistics.services.core.domain.Location;
import org.thinking.logistics.services.core.domain.command.ReplenishingCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface ReplenishingCommandRepository extends DomainRepository<ReplenishingCommand, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select cmd from ReplenishingCommand cmd " +
        "where cmd.goods = :goods " +
        "and cmd.batches = :batches " +
        "and cmd.targetLocation = :location " +
        "and cmd.commandType = 5" +
        "and cmd.stage < 99 " +
        "and cmd.availableQuantity > 0 " +
        "order by cmd.commandCategory, cmd.availableQuantity desc")
    List<ReplenishingCommand> acquireReplenishedCommands(Goods goods, Batches batches, Location location);
}
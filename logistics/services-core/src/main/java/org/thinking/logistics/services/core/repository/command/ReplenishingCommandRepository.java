package org.thinking.logistics.services.core.repository.command;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.entity.Batches;
import org.thinking.logistics.services.core.entity.Goods;
import org.thinking.logistics.services.core.entity.Location;
import org.thinking.logistics.services.core.entity.command.ReplenishingCommand;

import java.util.List;

@Repository
public interface ReplenishingCommandRepository extends JpaRepository<ReplenishingCommand, Long> {
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
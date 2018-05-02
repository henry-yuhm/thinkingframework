package org.thinking.logistics.batches.allocation.domain;

import org.thinking.logistics.services.core.domain.Batches;
import org.thinking.logistics.services.core.domain.Goods;
import org.thinking.logistics.services.core.domain.bill.OutboundDetail;
import org.thinking.logistics.services.core.domain.command.OutboundCommand;
import org.thinking.logistics.services.core.domain.inventory.Inventory;

import java.math.BigDecimal;

public interface Allocator {
    void verify() throws Exception;

    void initialize(OutboundDetail detail) throws Exception;

    void acquireBatchesInventory(OutboundDetail detail) throws Exception;

    void acquireBatches(boolean replenishing) throws Exception;

    void replenish(Goods goods, Batches batches, BigDecimal quantity) throws Exception;

    void acquireLocation(OutboundDetail detail) throws Exception;

    void appointLocation(OutboundDetail detail) throws Exception;

    void addInventory(Inventory inventory, BigDecimal quantity) throws Exception;

    void setDetail(OutboundDetail detail) throws Exception;

    OutboundCommand acquireCommand(OutboundDetail detail, Inventory inventory, BigDecimal quantity) throws Exception;

    void charge(Inventory inventory) throws Exception;

    void generateCommands(boolean directly, OutboundDetail detail) throws Exception;

    void check() throws Exception;

    void save();

    void allocate() throws Exception;
}
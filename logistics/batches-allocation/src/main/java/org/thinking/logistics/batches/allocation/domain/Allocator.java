package org.thinking.logistics.batches.allocation.domain;

import org.thinking.logistics.services.core.entity.Inventory;
import org.thinking.logistics.services.core.entity.bill.OutboundDetail;
import org.thinking.logistics.services.core.entity.bill.SupplementDetail;
import org.thinking.logistics.services.core.entity.command.OutboundCommand;

import java.math.BigDecimal;
import java.util.List;

public interface Allocator {
    void verify() throws Exception;

    void initialize(OutboundDetail detail) throws Exception;

    void initialize(SupplementDetail detail) throws Exception;

    void getBatchesDirectly(OutboundDetail detail) throws Exception;

    void getBatchesIndirectly(OutboundDetail detail) throws Exception;

    void apportionLocation() throws Exception;

    void appointLocation() throws Exception;

    void addInventory() throws Exception;

    void setDetail(OutboundDetail detail) throws Exception;

    int getCommandRows(BigDecimal quantity) throws Exception;

    void setCreationQuantity(OutboundCommand command, BigDecimal quantity) throws Exception;

    void setCommand(OutboundCommand command, Inventory inventory) throws Exception;

    void charge(Inventory inventory) throws Exception;

    void generateCommandsDirectly(List<Inventory> inventories) throws Exception;

    void generateCommandsIndirectly(List<Inventory> inventories) throws Exception;

    void check() throws Exception;

    void save();

    void allocate() throws Exception;
}
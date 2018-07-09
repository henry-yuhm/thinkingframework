package org.thinking.logistics.lot.allocation.domain;

import org.thinking.logistics.services.core.domain.command.ShipmentCommand;
import org.thinking.logistics.services.core.domain.common.Item;
import org.thinking.logistics.services.core.domain.common.Lot;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.inventory.Inventory;

import java.math.BigDecimal;

public interface Allocator {
    void verify() throws Exception;

    void initialize(ShipmentOrderDetail detail) throws Exception;

    void acquireLotInventory(ShipmentOrderDetail detail) throws Exception;

    void acquireLot(boolean replenishing) throws Exception;

    void replenish(Item item, Lot lot, BigDecimal quantity) throws Exception;

    void acquireLocation(ShipmentOrderDetail detail) throws Exception;

    void appointLocation(ShipmentOrderDetail detail) throws Exception;

    void addInventory(Inventory inventory, BigDecimal quantity) throws Exception;

    ShipmentCommand acquireCommand(ShipmentOrderDetail detail, Inventory inventory, BigDecimal quantity) throws Exception;

    void charge(Inventory inventory) throws Exception;

    void generateCommands(ShipmentOrderDetail detail, boolean directly) throws Exception;

    void check() throws Exception;

    void save();

    void allocate() throws Exception;
}
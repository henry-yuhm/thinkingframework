package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.command.OutboundCommand;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.inventory.Inventory;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppendantCommandAllocator extends AbstractAllocator {
    private OutboundCommand command;

    public AppendantCommandAllocator(OutboundOrderHeader header, OutboundCommand command) throws Exception {
        super(header);
        this.command = command;
    }

    @Override
    public void initialize(OutboundOrderDetail detail) throws Exception {
        super.initialize(detail);
    }

    @Override
    public OutboundCommand acquireCommand(OutboundOrderDetail detail, Inventory inventory, BigDecimal quantity) throws Exception {
        return super.acquireCommand(detail, inventory, quantity);
    }

    @Override
    public void allocate() throws Exception {

    }
}
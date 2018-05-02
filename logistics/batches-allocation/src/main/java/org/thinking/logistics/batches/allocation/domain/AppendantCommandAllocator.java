package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.bill.OutboundDetail;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;
import org.thinking.logistics.services.core.domain.command.OutboundCommand;
import org.thinking.logistics.services.core.domain.inventory.Inventory;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppendantCommandAllocator extends AbstractAllocator {
    private OutboundCommand command;

    public AppendantCommandAllocator(OutboundHeader header, OutboundCommand command) {
        super(header);
        this.command = command;
    }

    @Override
    public void initialize(OutboundDetail detail) throws Exception {
        super.initialize(detail);
    }

    @Override
    public void setDetail(OutboundDetail detail) throws Exception {
        super.setDetail(detail);
    }

    @Override
    public OutboundCommand acquireCommand(OutboundDetail detail, Inventory inventory, BigDecimal quantity) throws Exception {
        return super.acquireCommand(detail, inventory, quantity);
    }

    @Override
    public void allocate() throws Exception {

    }
}
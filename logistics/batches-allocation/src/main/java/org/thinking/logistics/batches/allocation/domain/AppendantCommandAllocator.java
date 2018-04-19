package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.entity.bill.OutboundDetail;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.entity.command.OutboundCommand;
import org.thinking.logistics.services.core.entity.inventory.Inventory;

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
    public void setCommand(OutboundCommand command, Inventory inventory) throws Exception {
        super.setCommand(command, inventory);
    }

    @Override
    public void allocate() throws Exception {

    }
}
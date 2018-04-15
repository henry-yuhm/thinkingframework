package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.entity.bill.OutboundDetail;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.entity.command.OutboundCommand;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuspendedOrderAllocator extends AbstractAllocator {
    public SuspendedOrderAllocator(OutboundHeader header) {
        super(header);
    }

    @Override
    public void setDetail(OutboundDetail detail) throws Exception {
        super.setDetail(detail);
    }

    @Override
    public int getCommandRows(BigDecimal quantity) throws Exception {
        return super.getCommandRows(quantity);
    }

    @Override
    public void setCreationQuantity(OutboundCommand command, BigDecimal quantity) throws Exception {
        super.setCreationQuantity(command, quantity);
    }

    @Override
    public void allocate() throws Exception {

    }
}
package org.thinking.logistics.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.domain.CompositeException;
import org.thinking.logistics.core.domain.support.OutboundStage;
import org.thinking.logistics.core.entity.Employee;
import org.thinking.logistics.core.entity.bill.OutboundHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class InsertedOrderDispatcher extends AbstractDispatcher {
    public InsertedOrderDispatcher(Employee operator, OutboundHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseBill() throws Exception {
        if (this.header.getStage().compareTo(OutboundStage.RELEASED) > 0) {
            throw CompositeException.getException("单据已经下发", this.operator, this.header, this.header.getOwner());
        }

        this.header.setWave("0000000005");

        super.releaseBill();
    }
}
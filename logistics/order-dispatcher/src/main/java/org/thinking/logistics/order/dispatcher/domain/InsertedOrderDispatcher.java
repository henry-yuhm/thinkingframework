package org.thinking.logistics.order.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.OutboundStage;

@Data
@EqualsAndHashCode(callSuper = true)
public class InsertedOrderDispatcher extends AbstractDispatcher {
    public InsertedOrderDispatcher(Employee operator, OutboundOrderHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseOrder() throws Exception {
        if (this.getHeader().getStage().compareTo(OutboundStage.RELEASED) > 0) {
            throw CompositeException.getException("单据已经下发", this.getOperator(), this.getHeader(), this.getHeader().getOwner());
        }

        this.getHeader().setWave("0000000005");

        super.releaseOrder();
    }
}
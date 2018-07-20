package org.thinking.sce.order.dispatcher.domain;

import lombok.*;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.employee.Employee;
import org.thinking.sce.service.core.domain.support.ShipmentStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class InsertedOrderDispatcher extends AbstractDispatcher {
    public InsertedOrderDispatcher(Employee operator, ShipmentOrderHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseOrder() throws Exception {
        if (this.getHeader().getShipmentStatus().compareTo(ShipmentStatus.RELEASED) > 0) {
            throw CompositeException.getException("单据已经下发", this.getOperator(), this.getHeader(), this.getHeader().getOwner());
        }

        this.getHeader().setWave("0000000005");

        super.releaseOrder();
    }
}
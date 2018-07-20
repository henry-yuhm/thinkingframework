package org.thinking.sce.order.dispatcher.domain;

import lombok.*;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.employee.Employee;
import org.thinking.sce.service.core.domain.support.ShipmentStatus;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class OutboundOrderDispatcher extends AbstractDispatcher {
    public OutboundOrderDispatcher(Employee operator, ShipmentOrderHeader header) {
        super(operator, header);
    }

    public OutboundOrderDispatcher(Employee operator, List<ShipmentOrderHeader> headers) {
        super(operator, headers);
    }

    @Override
    public void releaseWave() throws Exception {
        if (this.getHeaders().stream().anyMatch(header -> header.getShipmentStatus().compareTo(ShipmentStatus.RELEASED) >= 0)) {
            throw CompositeException.getException("波次已经下发");
        }

        super.releaseWave();
    }
}
package org.thinking.logistics.order.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.ShipmentStatus;

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
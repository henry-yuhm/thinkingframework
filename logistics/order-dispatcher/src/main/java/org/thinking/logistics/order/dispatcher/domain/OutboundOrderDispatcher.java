package org.thinking.logistics.order.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.OutboundStage;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class OutboundOrderDispatcher extends AbstractDispatcher {
    public OutboundOrderDispatcher(Employee operator, OutboundHeader header) {
        super(operator, header);
    }

    public OutboundOrderDispatcher(Employee operator, List<OutboundHeader> headers) {
        super(operator, headers);
    }

    @Override
    public void releaseWave() throws Exception {
        if (this.headers.stream().anyMatch(header -> header.getStage().compareTo(OutboundStage.RELEASED) >= 0)) {
            throw CompositeException.getException("波次已经下发");
        }

        super.releaseWave();
    }
}
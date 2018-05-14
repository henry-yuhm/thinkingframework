package org.thinking.logistics.order.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppointedLocationDispatcher extends AbstractDispatcher {
    public AppointedLocationDispatcher(Employee operator, OutboundOrderHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseOrder() throws Exception {
        this.getHeader().isResended();

        this.getHeader().setWave("0000000006");

        super.releaseOrder();
    }
}
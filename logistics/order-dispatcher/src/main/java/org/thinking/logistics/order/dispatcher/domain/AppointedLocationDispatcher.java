package org.thinking.logistics.order.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.entity.employee.Employee;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppointedLocationDispatcher extends AbstractDispatcher {
    public AppointedLocationDispatcher(Employee operator, OutboundHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseOrder() throws Exception {
        this.header.isResended();

        this.header.setWave("0000000006");

        super.releaseOrder();
    }
}
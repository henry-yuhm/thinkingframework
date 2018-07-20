package org.thinking.sce.order.dispatcher.domain;

import lombok.*;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.employee.Employee;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppointedLocationDispatcher extends AbstractDispatcher {
    public AppointedLocationDispatcher(Employee operator, ShipmentOrderHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseOrder() throws Exception {
        this.getHeader().isResended();

        this.getHeader().setWave("0000000006");

        super.releaseOrder();
    }
}
package org.thinking.sce.order.dispatcher.domain;

import lombok.*;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.employee.Employee;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuspendedOrderDispatcher extends AbstractDispatcher {
    public SuspendedOrderDispatcher(Employee operator, ShipmentOrderHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseOrder() throws Exception {
        this.getHeader().isResended();

        //region 骨科寄售校验
        //endregion

        super.releaseOrder();
    }
}
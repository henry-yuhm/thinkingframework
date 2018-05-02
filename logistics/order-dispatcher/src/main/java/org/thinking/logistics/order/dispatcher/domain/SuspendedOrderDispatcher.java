package org.thinking.logistics.order.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuspendedOrderDispatcher extends AbstractDispatcher {
    public SuspendedOrderDispatcher(Employee operator, OutboundHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseOrder() throws Exception {
        this.header.isResended();

        //region 骨科寄售校验
        //endregion

        super.releaseOrder();
    }
}
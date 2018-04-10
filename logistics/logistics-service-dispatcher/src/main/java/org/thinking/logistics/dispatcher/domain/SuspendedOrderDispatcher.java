package org.thinking.logistics.dispatcher.domain;

import org.thinking.logistics.core.entity.Employee;
import org.thinking.logistics.core.entity.bill.OutboundHeader;

public class SuspendedOrderDispatcher extends AbstractDispatcher {
    public SuspendedOrderDispatcher(Employee operator, OutboundHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseBill() throws Exception {
        this.header.isResended();

        //region 骨科寄售校验
        //endregion

        super.releaseBill();
    }
}
package org.thinking.logistics.dispatcher.domain;

import org.thinking.logistics.core.entity.Employee;
import org.thinking.logistics.core.entity.bill.OutboundHeader;

public class SpecialOrderDispatcher extends AbstractDispatcher {
    public SpecialOrderDispatcher(Employee operator, OutboundHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseBill() throws Exception {
        //region 特殊订单下发
        //endregion
    }
}
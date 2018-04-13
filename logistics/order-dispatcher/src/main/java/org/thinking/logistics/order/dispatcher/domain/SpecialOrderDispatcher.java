package org.thinking.logistics.order.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.entity.Employee;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecialOrderDispatcher extends AbstractDispatcher {
    public SpecialOrderDispatcher(Employee operator, OutboundHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseOrder() throws Exception {
        //region 特殊订单下发
        //endregion
    }
}
package org.thinking.logistics.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.entity.Employee;
import org.thinking.logistics.core.entity.bill.OutboundHeader;

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
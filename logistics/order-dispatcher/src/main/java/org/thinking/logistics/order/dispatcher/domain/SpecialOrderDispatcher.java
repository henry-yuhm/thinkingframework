package org.thinking.logistics.order.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecialOrderDispatcher extends AbstractDispatcher {
    public SpecialOrderDispatcher(Employee operator, ShipmentOrderHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseOrder() throws Exception {
        //region 特殊订单下发
        //endregion
    }
}
package org.thinking.sce.order.reversor.domain;

import lombok.*;
import org.thinking.sce.service.core.domain.document.*;
import org.thinking.sce.service.core.domain.employee.Employee;
import org.thinking.sce.service.core.domain.support.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnReversor extends AbstractReversor {
    public PurchaseReturnReversor(Employee operator, ShipmentOrderHeader header, ReversionStage reversionStage) {
        super(operator, header, reversionStage);
    }

    @Override
    public void revert() {
        List<ShipmentOrderDetail> details = this.getHeader().getDetails()
            .stream()
            .filter(detail -> detail.getExpectedQuantity().compareTo(detail.getActualQuantity()) != 0)
            .collect(Collectors.toList());

        if (details == null || details.size() == 0) {
            return;
        }

        details.forEach(this::revert);

        //整单冲红处理
        if (this.getOrderService().isReversed(this.getHeader())) {
            this.getHeader().setShipmentStatus(ShipmentStatus.TASK_COMPLETE);
            this.getHeader().setReversed(true);
            this.getHeader().setTaskCompleteTime(Instant.now());
        }

        this.getOrderService().getRepository().save(this.getHeader());
    }
}
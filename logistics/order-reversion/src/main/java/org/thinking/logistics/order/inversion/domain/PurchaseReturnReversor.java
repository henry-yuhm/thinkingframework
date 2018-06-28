package org.thinking.logistics.order.inversion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.OutboundStage;
import org.thinking.logistics.services.core.domain.support.ReversionStage;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnReversor extends AbstractReversor {
    public PurchaseReturnReversor(Employee operator, ShipmentOrderHeader header, ReversionStage stage) {
        super(operator, header, stage);
    }

    @Override
    public void revert() {
        List<ShipmentOrderDetail> details = this.getHeader().getDetails()
            .stream()
            .filter(detail -> detail.getPlanQuantity().compareTo(detail.getFactQuantity()) != 0)
            .collect(Collectors.toList());

        if (details == null || details.size() == 0) {
            return;
        }

        details.forEach(this::revert);

        //整单冲红处理
        if (this.getOrderService().isReversed(this.getHeader())) {
            this.getHeader().setStage(OutboundStage.TASK_COMPLETE);
            this.getHeader().setReversed(true);
            this.getHeader().setTaskCompleteTime(Instant.now());
        }

        this.getOrderService().getRepository().save(this.getHeader());
    }
}
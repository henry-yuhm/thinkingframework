package org.thinking.logistics.order.inversion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.InverseStage;
import org.thinking.logistics.services.core.domain.support.OutboundStage;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnInverser extends AbstractInverser {
    public PurchaseReturnInverser(Employee operator, ShipmentOrderHeader header, InverseStage stage) {
        super(operator, header, stage);
    }

    @Override
    public void inverse() {
        List<ShipmentOrderDetail> details = this.getHeader().getDetails()
            .stream()
            .filter(detail -> detail.getPlanQuantity().compareTo(detail.getFactQuantity()) != 0)
            .collect(Collectors.toList());

        if (details == null || details.size() == 0) {
            return;
        }

        details.forEach(this::inverse);

        //整单冲红处理
        if (this.getOrderService().isInversed(this.getHeader())) {
            this.getHeader().setStage(OutboundStage.TASK_COMPLETE);
            this.getHeader().setInversed(true);
            this.getHeader().setTaskCompleteTime(Date.valueOf(LocalDate.now()));
        }

        this.getOrderService().getRepository().save(this.getHeader());
    }
}
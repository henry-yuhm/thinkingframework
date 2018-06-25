package org.thinking.logistics.order.inversion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.document.InverseOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.InverseStage;
import org.thinking.logistics.services.core.domain.support.OutboundStage;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOutboundInverser extends AbstractInverser {
    public SaleOutboundInverser(Employee operator, ShipmentOrderHeader header, InverseStage stage) {
        super(operator, header, stage);
    }

    @Override
    public void inverse() {
        List<ShipmentOrderDetail> details = this.getHeader().getDetails()
            .stream()
            .filter(detail -> detail.isOriginal() && ((this.getStage() == InverseStage.DISPATCHING && detail.getPlanQuantity().compareTo(detail.getFactQuantity()) != 0) || (this.getStage() == InverseStage.SUSPENDING && detail.getLessnessQuantity().compareTo(BigDecimal.ZERO) > 0)))
            .collect(Collectors.toList());

        if (details == null || details.size() == 0) {
            return;
        }

        details.forEach(this::inverse);

        //整单冲红处理
        if (this.getOrderService().isInversed(this.getHeader())) {
            this.getHeader().setStage(OutboundStage.TASK_COMPLETE);
            this.getHeader().setInversed(true);
            this.getHeader().setTaskCompleteTime(Instant.now());
        }

        if (this.getStage() == InverseStage.SUSPENDING) {
            this.getHeader().getDetails().forEach(detail -> detail.setLessnessQuantity(BigDecimal.ZERO));
        }

        this.getOrderService().getRepository().save(this.getHeader());
    }

    @Override
    public void audit(List<InverseOrderDetail> details) throws Exception {
        super.audit(details);

        if (this.getHeader().isInversed())
        //配送冲红
        {
            return;
        }
    }
}
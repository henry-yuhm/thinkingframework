package org.thinking.logistics.order.reversal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.document.ReversionNoteDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.OutboundStage;
import org.thinking.logistics.services.core.domain.support.ReversionStage;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOutboundReversor extends AbstractReversor {
    public SaleOutboundReversor(Employee operator, ShipmentOrderHeader header, ReversionStage stage) {
        super(operator, header, stage);
    }

    @Override
    public void revert() {
        List<ShipmentOrderDetail> details = this.getHeader().getDetails()
            .stream()
            .filter(detail -> detail.isOriginal() && ((this.getStage() == ReversionStage.DISPATCHING && detail.getPlanQuantity().compareTo(detail.getFactQuantity()) != 0) || (this.getStage() == ReversionStage.SUSPENDING && detail.getLessnessQuantity().compareTo(BigDecimal.ZERO) > 0)))
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

        if (this.getStage() == ReversionStage.SUSPENDING) {
            this.getHeader().getDetails().forEach(detail -> detail.setLessnessQuantity(BigDecimal.ZERO));
        }

        this.getOrderService().getRepository().save(this.getHeader());
    }

    @Override
    public void audit(List<ReversionNoteDetail> details) throws Exception {
        super.audit(details);

        if (this.getHeader().isReversed())
        //配送冲红
        {
            return;
        }
    }
}
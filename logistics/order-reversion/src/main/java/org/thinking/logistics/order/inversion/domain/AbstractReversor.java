package org.thinking.logistics.order.inversion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessBase;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.document.ReversionNoteDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.OutboundStage;
import org.thinking.logistics.services.core.domain.support.ReversionStage;
import org.thinking.logistics.services.core.service.document.ReversionNoteService;
import org.thinking.logistics.services.core.service.document.ShipmentOrderService;
import org.thinking.logistics.services.core.service.stagingarea.StagingareaService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractReversor extends BusinessBase implements Reversor {
    private ShipmentOrderHeader header;

    private ReversionStage stage;

    @Resource
    private ShipmentOrderService orderService;

    @Resource
    private ReversionNoteService reversionNoteService;

    @Resource
    private StagingareaService stagingareaService;

    public AbstractReversor(Employee operator, ShipmentOrderHeader header, ReversionStage stage) {
        super(operator);
        this.header = header;
        this.stage = stage;
    }

    @Override
    public void revert(ShipmentOrderDetail detail) {
        ReversionNoteDetail reversionNoteDetail = this.reversionNoteService.acquire(detail.getWarehouse(), detail, this.stage);

        if (reversionNoteDetail == null) {
            reversionNoteDetail = new ReversionNoteDetail();

            this.calculateQuantity(detail, reversionNoteDetail);
            if (reversionNoteDetail.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
                return;
            }

            reversionNoteDetail.setWarehouse(detail.getWarehouse());
            reversionNoteDetail.setItem(detail.getItem());
            reversionNoteDetail.setLot(detail.getLot());
            reversionNoteDetail.setHeader(this.header);
            reversionNoteDetail.setDetail(detail);
            reversionNoteDetail.setOperator(this.getOperator());
            reversionNoteDetail.setStage(this.stage);
        } else {
            this.calculateQuantity(detail, reversionNoteDetail);
        }

        this.reversionNoteService.getRepository().save(reversionNoteDetail);
    }

    @Override
    public void calculateQuantity(ShipmentOrderDetail detail, ReversionNoteDetail reversionNoteDetail) {
        if (this.stage == ReversionStage.DISPATCHING || this.stage == ReversionStage.EXECUTING) {
            reversionNoteDetail.setQuantity(detail.getPlanQuantity().subtract(detail.getFactQuantity()));
        } else if (this.stage == ReversionStage.SUSPENDING) {
            reversionNoteDetail.setQuantity(detail.getLessnessQuantity());
        }
    }

    @Override
    public void audit(List<ReversionNoteDetail> details) throws Exception {
        if (this.header.isApproved()) {
            throw CompositeException.getException("单据已冲红审核，不能重复审核", this.getOperator(), this.header, this.header.getOwner());
        }

        if (this.header.getStage().compareTo(OutboundStage.TASK_COMPLETE) < 0) {
            throw CompositeException.getException("单据未内复核完成，不能审核", this.getOperator(), this.header, this.header.getOwner());
        }

        if (this.header.isReversed()) {
            this.header.setStage(OutboundStage.RECHECK_COMPLETE);

            //整单冲红且分配月台的，自动清理月台
            this.stagingareaService.cleanup(this.header);
        }

        this.header.setApproved(true);
        this.orderService.getRepository().save(this.header);

        details.forEach(detail -> {
            detail.setAuditor(this.getOperator());
            detail.setAuditTime(Instant.now());
        });
        this.reversionNoteService.getRepository().saveAll(details);

        //上传
    }
}
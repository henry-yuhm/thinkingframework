package org.thinking.sce.order.reversor.domain;

import lombok.*;
import org.thinking.sce.service.core.domain.*;
import org.thinking.sce.service.core.domain.document.*;
import org.thinking.sce.service.core.domain.employee.Employee;
import org.thinking.sce.service.core.domain.support.*;
import org.thinking.sce.service.core.service.document.*;
import org.thinking.sce.service.core.service.stagingarea.StagingareaService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractReversor extends BusinessBase implements Reversor {
    private ShipmentOrderHeader header;

    private ReversionStage reversionStage;

    @Resource
    private ShipmentOrderService orderService;

    @Resource
    private ReversionNoteService reversionNoteService;

    @Resource
    private StagingareaService stagingareaService;

    public AbstractReversor(Employee operator, ShipmentOrderHeader header, ReversionStage reversionStage) {
        super(operator);
        this.header = header;
        this.reversionStage = reversionStage;
    }

    @Override
    public void revert(ShipmentOrderDetail detail) {
        ReversionNoteDetail reversionNoteDetail = this.reversionNoteService.acquire(detail.getWarehouse(), detail, this.reversionStage);

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
            reversionNoteDetail.setReversionStage(this.reversionStage);
        } else {
            this.calculateQuantity(detail, reversionNoteDetail);
        }

        this.reversionNoteService.getRepository().save(reversionNoteDetail);
    }

    @Override
    public void calculateQuantity(ShipmentOrderDetail detail, ReversionNoteDetail reversionNoteDetail) {
        if (this.reversionStage == ReversionStage.DISPATCHING || this.reversionStage == ReversionStage.EXECUTING) {
            reversionNoteDetail.setQuantity(detail.getExpectedQuantity().subtract(detail.getActualQuantity()));
        } else if (this.reversionStage == ReversionStage.SUSPENDING) {
            reversionNoteDetail.setQuantity(detail.getLessnessQuantity());
        }
    }

    @Override
    public void audit(List<ReversionNoteDetail> details) throws Exception {
        if (this.header.isApproved()) {
            throw CompositeException.getException("单据已冲红审核，不能重复审核", this.getOperator(), this.header, this.header.getOwner());
        }

        if (this.header.getShipmentStatus().compareTo(ShipmentStatus.TASK_COMPLETE) < 0) {
            throw CompositeException.getException("单据未内复核完成，不能审核", this.getOperator(), this.header, this.header.getOwner());
        }

        if (this.header.isReversed()) {
            this.header.setShipmentStatus(ShipmentStatus.REVIEWED_COMPLETE);

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
package org.thinking.logistics.order.inversion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessBase;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.documents.InverseOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.InverseStage;
import org.thinking.logistics.services.core.domain.support.OutboundStage;
import org.thinking.logistics.services.core.service.documents.InverseOrderService;
import org.thinking.logistics.services.core.service.documents.OutboundOrderService;
import org.thinking.logistics.services.core.service.stagingarea.StagingareaService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractInverser extends BusinessBase implements Inverser {
    private OutboundOrderHeader header;

    private InverseStage stage;

    @Resource
    private OutboundOrderService orderService;

    @Resource
    private InverseOrderService inverseOrderService;

    @Resource
    private StagingareaService stagingareaService;

    public AbstractInverser(Employee operator, OutboundOrderHeader header, InverseStage stage) {
        super(operator);
        this.header = header;
        this.stage = stage;
    }

    @Override
    public void inverse(OutboundOrderDetail detail) {
        InverseOrderDetail inverseOrderDetail = this.inverseOrderService.acquire(detail.getWarehouse(), detail, this.stage);

        if (inverseOrderDetail == null) {
            inverseOrderDetail = new InverseOrderDetail();

            this.calculateQuantity(inverseOrderDetail, detail);
            if (inverseOrderDetail.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
                return;
            }

            inverseOrderDetail.setWarehouse(detail.getWarehouse());
            inverseOrderDetail.setGoods(detail.getGoods());
            inverseOrderDetail.setBatches(detail.getBatches());
            inverseOrderDetail.setHeader(this.header);
            inverseOrderDetail.setDetail(detail);
            inverseOrderDetail.setOperator(this.getOperator());
            inverseOrderDetail.setStage(this.stage);
        } else {
            this.calculateQuantity(inverseOrderDetail, detail);
        }

        this.inverseOrderService.getRepository().save(inverseOrderDetail);
    }

    @Override
    public void calculateQuantity(InverseOrderDetail inverseOrderDetail, OutboundOrderDetail detail) {
        if (this.stage == InverseStage.DISPATCHING || this.stage == InverseStage.EXECUTING) {
            inverseOrderDetail.setQuantity(detail.getPlanQuantity().subtract(detail.getFactQuantity()));
        } else if (this.stage == InverseStage.SUSPENDING) {
            inverseOrderDetail.setQuantity(detail.getLessnessQuantity());
        }
    }

    @Override
    public void audit(List<InverseOrderDetail> details) throws Exception {
        if (this.header.isAudited()) {
            throw CompositeException.getException("单据已冲红审核，不能重复审核", this.getOperator(), this.header, this.header.getOwner());
        }

        if (this.header.getStage().compareTo(OutboundStage.TASK_COMPLETE) < 0) {
            throw CompositeException.getException("单据未内复核完成，不能审核", this.getOperator(), this.header, this.header.getOwner());
        }

        if (this.header.isInversed()) {
            this.header.setStage(OutboundStage.RECHECK_COMPLETE);

            //整单冲红且分配月台的，自动清理月台
            this.stagingareaService.cleanup(this.header);
        }

        this.header.setAudited(true);
        this.orderService.getRepository().save(this.header);

        details.forEach(detail -> {
            detail.setAuditor(this.getOperator());
            detail.setAuditTime(Date.valueOf(LocalDate.now()));
        });
        this.inverseOrderService.getRepository().saveAll(details);

        //上传
    }
}
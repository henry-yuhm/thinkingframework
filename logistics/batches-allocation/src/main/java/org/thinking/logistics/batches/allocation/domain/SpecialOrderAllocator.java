package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.order.inversion.domain.SaleOutboundInverser;
import org.thinking.logistics.services.core.domain.command.OutboundCommand;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.inventory.Inventory;
import org.thinking.logistics.services.core.domain.support.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecialOrderAllocator extends AbstractAllocator {
    public SpecialOrderAllocator(OutboundOrderHeader header) throws Exception {
        super(header);
    }

    @Override
    public void acquireLocation(OutboundOrderDetail detail) throws Exception {
        super.acquireLocation(detail);
    }

    @Override
    public OutboundCommand acquireCommand(OutboundOrderDetail detail, Inventory inventory, BigDecimal quantity) throws Exception {
        OutboundCommand command = super.acquireCommand(detail, inventory, quantity);

        command.setStage(CommandStage.INNER_RECHECK_CONFIRM);
        command.setWorkMode(WorkMode.PAPER);
        command.setActivated(true);
        if (command.getTask().getPicker() == null) {
            command.getTask().setPicker(this.getOperator());
            command.getTask().setPickingStartTime(Date.valueOf(LocalDate.now()));
            command.getTask().setPickingCompleteTime(Date.valueOf(LocalDate.now()));
        }

        return command;
    }

    @Override
    public void save() {
        this.getHeader().setStage(OutboundStage.TASK_COMPLETE);
        this.getHeader().setDispatchers(this.getOperator());
        this.getHeader().setDispatcherTime(Date.valueOf(LocalDate.now()));
        this.getHeader().setReleaseTime(Date.valueOf(LocalDate.now()));
        this.getHeader().setTaskCompleteTime(Date.valueOf(LocalDate.now()));
        this.getHeader().setReportbillPrintSign(PrintSign.CONFIRMATION);
        this.getHeader().setReportbillPrintClerk(this.getOperator());
        this.getHeader().setReportbillPrintTime(Date.valueOf(LocalDate.now()));

        this.getOrderService().getRepository().save(this.getHeader());
    }

    @Override
    public void allocate() throws Exception {
        //校验单据
        this.verify();

        //获取原始行数据
        List<OutboundOrderDetail> originalDetails = this.getHeader().getDetails().stream().filter(OutboundOrderDetail::isOriginal).sorted(Comparator.comparing(OutboundOrderDetail::getId)).collect(Collectors.toList());

        //按原始行循环
        for (OutboundOrderDetail originalDetail : originalDetails) {
            //获取补发行数据
            List<OutboundOrderDetail> unoriginalDetails = this.getHeader().getDetails().stream().filter(d -> d.getParent() == originalDetail && !d.isOriginal()).sorted(Comparator.comparing(OutboundOrderDetail::getId)).collect(Collectors.toList());

            //如果没有索取补发行，则按原始行分配
            if (unoriginalDetails == null || unoriginalDetails.size() == 0) {
                unoriginalDetails = new LinkedList<>();
                unoriginalDetails.add(originalDetail);
            }

            //分配批号
            for (OutboundOrderDetail unoriginalDetail : unoriginalDetails) {
                if (unoriginalDetail.getWholepiecesQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getInventories().clear();

                    this.setPackageType(PackageType.WHOLEPIECES);
                    this.setAllocationQuantity(unoriginalDetail.getWholepiecesQuantity());

                    this.appointLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, true);

                    unoriginalDetail.setFactQuantity(unoriginalDetail.getFactQuantity().subtract(this.getAllocationQuantity()));
                }

                if (unoriginalDetail.getRemainderQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getInventories().clear();

                    this.setPackageType(PackageType.REMAINDER);
                    this.setAllocationQuantity(unoriginalDetail.getRemainderQuantity());

                    this.appointLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, true);

                    unoriginalDetail.setFactQuantity(unoriginalDetail.getFactQuantity().subtract(this.getAllocationQuantity()));
                }
            }

            //region 更新原始行
            originalDetail.setFactQuantity(Optional.ofNullable(this.getHeader().getDetails().stream().filter(d -> d.getParent() == originalDetail && !d.isOriginal()).map(OutboundOrderDetail::getFactQuantity).reduce(BigDecimal.ZERO, BigDecimal::add)).orElse(originalDetail.getFactQuantity()));
            originalDetail.setWholepiecesQuantity(BigDecimal.ZERO);
            originalDetail.setRemainderQuantity(BigDecimal.ZERO);
            originalDetail.setLessnessQuantity(BigDecimal.ZERO);
            //endregion
        }

        this.save();

        //冲红
        new SaleOutboundInverser(this.getHeader().getDispatchers(), this.getHeader(), InverseStage.DISPATCHING).inverse();

        //校验指令
        this.verify();
    }
}
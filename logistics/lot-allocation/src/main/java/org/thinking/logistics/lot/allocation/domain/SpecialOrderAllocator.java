package org.thinking.logistics.lot.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.order.inversion.domain.SaleOutboundReversor;
import org.thinking.logistics.services.core.domain.command.ShipmentCommand;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.inventory.Inventory;
import org.thinking.logistics.services.core.domain.support.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecialOrderAllocator extends AbstractAllocator {
    public SpecialOrderAllocator(ShipmentOrderHeader header) throws Exception {
        super(header);
    }

    @Override
    public void acquireLocation(ShipmentOrderDetail detail) throws Exception {
        super.acquireLocation(detail);
    }

    @Override
    public ShipmentCommand acquireCommand(ShipmentOrderDetail detail, Inventory inventory, BigDecimal quantity) throws Exception {
        ShipmentCommand command = super.acquireCommand(detail, inventory, quantity);

        command.setCommandStatus(CommandStatus.INNERREVIEW_CONFIRMED);
        command.setWorkMode(WorkMode.PAPER);
        command.setActivated(true);
        if (command.getTask().getPicker() == null) {
            command.getTask().setPicker(this.getOperator());
            command.getTask().setPickingStartTime(Instant.now());
            command.getTask().setPickingCompleteTime(Instant.now());
        }

        return command;
    }

    @Override
    public void save() {
        this.getHeader().setShipmentStatus(ShipmentStatus.TASK_COMPLETE);
        this.getHeader().setDispatchers(this.getOperator());
        this.getHeader().setDispatcherTime(Instant.now());
        this.getHeader().setReleaseTime(Instant.now());
        this.getHeader().setTaskCompleteTime(Instant.now());
        this.getHeader().setReportListPrintSign(PrintSign.CONFIRMATION);
        this.getHeader().setReportListPrintClerk(this.getOperator());
        this.getHeader().setReportListPrintTime(Instant.now());

        this.getOrderService().getRepository().save(this.getHeader());
    }

    @Override
    public void allocate() throws Exception {
        //校验单据
        this.verify();

        //获取原始行数据
        List<ShipmentOrderDetail> originalDetails = this.getHeader().getDetails().stream().filter(ShipmentOrderDetail::isOriginal).sorted(Comparator.comparing(ShipmentOrderDetail::getId)).collect(Collectors.toList());

        //按原始行循环
        for (ShipmentOrderDetail originalDetail : originalDetails) {
            //获取补发行数据
            List<ShipmentOrderDetail> unoriginalDetails = this.getHeader().getDetails().stream().filter(d -> d.getParent() == originalDetail && !d.isOriginal()).sorted(Comparator.comparing(ShipmentOrderDetail::getId)).collect(Collectors.toList());

            //如果没有索取补发行，则按原始行分配
            if (unoriginalDetails == null || unoriginalDetails.size() == 0) {
                unoriginalDetails = new LinkedList<>();
                unoriginalDetails.add(originalDetail);
            }

            //分配批号
            for (ShipmentOrderDetail unoriginalDetail : unoriginalDetails) {
                if (unoriginalDetail.getCasesQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getInventories().clear();
                    this.getCommands().clear();

                    this.setPackageType(PackageType.WHOLEPIECES);
                    this.setAllocationQuantity(unoriginalDetail.getCasesQuantity());

                    this.appointLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, true);

                    unoriginalDetail.setActualQuantity(unoriginalDetail.getActualQuantity().subtract(this.getAllocationQuantity()));
                }

                if (unoriginalDetail.getRemainderQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getInventories().clear();
                    this.getCommands().clear();

                    this.setPackageType(PackageType.REMAINDER);
                    this.setAllocationQuantity(unoriginalDetail.getRemainderQuantity());

                    this.appointLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, true);

                    unoriginalDetail.setActualQuantity(unoriginalDetail.getActualQuantity().subtract(this.getAllocationQuantity()));
                }
            }

            //region 更新原始行
            originalDetail.setActualQuantity(Optional.ofNullable(this.getHeader().getDetails().stream().filter(d -> d.getParent() == originalDetail && !d.isOriginal()).map(ShipmentOrderDetail::getActualQuantity).reduce(BigDecimal.ZERO, BigDecimal::add)).orElse(originalDetail.getActualQuantity()));
            originalDetail.setCasesQuantity(BigDecimal.ZERO);
            originalDetail.setRemainderQuantity(BigDecimal.ZERO);
            originalDetail.setLessnessQuantity(BigDecimal.ZERO);
            //endregion
        }

        this.save();

        //冲红
        new SaleOutboundReversor(this.getHeader().getDispatchers(), this.getHeader(), ReversionStage.DISPATCHING).revert();

        //校验指令
        this.verify();
    }
}
package org.thinking.sce.inventory.allocator.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.order.reversor.domain.SaleOutboundReversor;
import org.thinking.sce.service.core.domain.document.ShipmentOrderDetail;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.support.PackageType;
import org.thinking.sce.service.core.domain.support.ReversionStage;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppointedLocationAllocator extends AbstractAllocator {
    public AppointedLocationAllocator(ShipmentOrderHeader header) throws Exception {
        super(header);
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

            //分配批号
            for (ShipmentOrderDetail unoriginalDetail : unoriginalDetails) {
                this.initialize(unoriginalDetail);

                if (unoriginalDetail.getCasesQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getInventories().clear();
                    this.getCommands().clear();

                    this.setPackageType(PackageType.WHOLEPIECES);
                    this.setAllocationQuantity(unoriginalDetail.getCasesQuantity());

                    this.appointLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, false);

                    unoriginalDetail.setActualQuantity(unoriginalDetail.getActualQuantity().subtract(this.getAllocationQuantity()));
                    unoriginalDetail.setCasesQuantity(BigDecimal.ZERO);
                }

                if (unoriginalDetail.getRemainderQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getInventories().clear();
                    this.getCommands().clear();

                    this.setPackageType(PackageType.REMAINDER);
                    this.setAllocationQuantity(unoriginalDetail.getRemainderQuantity());

                    this.appointLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, false);

                    unoriginalDetail.setActualQuantity(unoriginalDetail.getActualQuantity().subtract(this.getAllocationQuantity()));
                    unoriginalDetail.setRemainderQuantity(BigDecimal.ZERO);
                }
            }

            //region 更新原始行
            originalDetail.setActualQuantity(Optional.ofNullable(this.getHeader().getDetails().stream().filter(d -> d.getParent() == originalDetail && !d.isOriginal()).map(ShipmentOrderDetail::getActualQuantity).reduce(BigDecimal.ZERO, BigDecimal::add)).orElse(BigDecimal.ZERO));
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
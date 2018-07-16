package org.thinking.logistics.inventory.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.order.reversal.domain.SaleOutboundReversor;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.domain.support.ReversionStage;
import org.thinking.logistics.services.core.domain.support.StorageClassification;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuspendedOrderAllocator extends AbstractAllocator {
    public SuspendedOrderAllocator(ShipmentOrderHeader header) throws Exception {
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
                if (unoriginalDetail.getCasesQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getLots().clear();
                    this.getLotInventories().clear();
                    this.getInventories().clear();
                    this.getCommands().clear();

                    this.setPackageType(PackageType.WHOLEPIECES);
                    this.setAllocationQuantity(unoriginalDetail.getCasesQuantity());

                    this.acquireLotInventory(unoriginalDetail);
                    this.acquireLot(false);

                    this.acquireLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, false);

                    unoriginalDetail.setCasesQuantity(this.getAllocationQuantity());
                }

                if (unoriginalDetail.getRemainderQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getLots().clear();
                    this.getLotInventories().clear();
                    this.getInventories().clear();
                    this.getCommands().clear();

                    this.setPackageType(PackageType.REMAINDER);
                    this.setAllocationQuantity(unoriginalDetail.getRemainderQuantity());

                    this.acquireLotInventory(unoriginalDetail);
                    this.acquireLot(false);

                    if (this.getAllocationQuantity().compareTo(BigDecimal.ZERO) > 0 && unoriginalDetail.getItem().getStorageClassification() == StorageClassification.ALL) {
                        this.setAllocationQuantity(unoriginalDetail.getRemainderQuantity());
                        this.acquireLot(true);
                    }

                    this.acquireLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, false);

                    unoriginalDetail.setRemainderQuantity(this.getAllocationQuantity());
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
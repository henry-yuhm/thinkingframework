package org.thinking.logistics.lot.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.order.inversion.domain.SaleOutboundInverser;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.support.InverseStage;
import org.thinking.logistics.services.core.domain.support.PackageType;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnAllocator extends AbstractAllocator {
    public PurchaseReturnAllocator(ShipmentOrderHeader header) throws Exception {
        super(header);
    }

    @Override
    public void initialize(ShipmentOrderDetail detail) throws Exception {
        //零货出整件处理
        if (detail.getWholepiecesQuantity().compareTo(BigDecimal.ZERO) > 0) {
            if (detail.getLocation() != null && detail.getLocation().getPackageType() == PackageType.REMAINDER) {
                detail.setWholepiecesQuantity(BigDecimal.ZERO);
                detail.setRemainderQuantity(detail.getFactQuantity());
            }
        }
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

                if (unoriginalDetail.getWholepiecesQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getInventories().clear();
                    this.getCommands().clear();

                    this.setPackageType(PackageType.WHOLEPIECES);
                    this.setAllocationQuantity(unoriginalDetail.getWholepiecesQuantity());

                    this.appointLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, true);

                    unoriginalDetail.setFactQuantity(unoriginalDetail.getFactQuantity().subtract(this.getAllocationQuantity()));
                    unoriginalDetail.setWholepiecesQuantity(BigDecimal.ZERO);
                }

                if (unoriginalDetail.getRemainderQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getInventories().clear();
                    this.getCommands().clear();

                    this.setPackageType(PackageType.REMAINDER);
                    this.setAllocationQuantity(unoriginalDetail.getRemainderQuantity());

                    this.appointLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, true);

                    unoriginalDetail.setFactQuantity(unoriginalDetail.getFactQuantity().subtract(this.getAllocationQuantity()));
                    unoriginalDetail.setRemainderQuantity(BigDecimal.ZERO);
                }
            }

            //region 更新原始行
            originalDetail.setFactQuantity(Optional.ofNullable(this.getHeader().getDetails().stream().filter(d -> d.getParent() == originalDetail && !d.isOriginal()).map(ShipmentOrderDetail::getPlanQuantity).reduce(BigDecimal.ZERO, BigDecimal::add)).orElse(BigDecimal.ZERO));
            originalDetail.setWholepiecesQuantity(BigDecimal.ZERO);
            originalDetail.setRemainderQuantity(BigDecimal.ZERO);
            originalDetail.setLessnessQuantity(BigDecimal.ZERO);
            //endregion
        }

        this.save();

        //冲红
        new SaleOutboundInverser(this.getHeader().getDispatchers(), this.getHeader(), InverseStage.EXECUTING).inverse();

        //校验指令
        this.verify();
    }
}
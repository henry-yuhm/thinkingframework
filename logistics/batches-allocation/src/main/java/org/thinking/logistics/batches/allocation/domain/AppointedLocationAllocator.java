package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.order.inversion.domain.SaleOutboundInverser;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.support.InverseStage;
import org.thinking.logistics.services.core.domain.support.PackageType;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppointedLocationAllocator extends AbstractAllocator {
    public AppointedLocationAllocator(OutboundOrderHeader header) throws Exception {
        super(header);
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

            //分配批号
            for (OutboundOrderDetail unoriginalDetail : unoriginalDetails) {
                this.initialize(unoriginalDetail);

                if (unoriginalDetail.getWholepiecesQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getInventories().clear();
                    this.getCommands().clear();

                    this.setPackageType(PackageType.WHOLEPIECES);
                    this.setAllocationQuantity(unoriginalDetail.getWholepiecesQuantity());

                    this.appointLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, false);

                    unoriginalDetail.setFactQuantity(unoriginalDetail.getFactQuantity().subtract(this.getAllocationQuantity()));
                    unoriginalDetail.setWholepiecesQuantity(BigDecimal.ZERO);
                }

                if (unoriginalDetail.getRemainderQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    this.getInventories().clear();
                    this.getCommands().clear();

                    this.setPackageType(PackageType.REMAINDER);
                    this.setAllocationQuantity(unoriginalDetail.getRemainderQuantity());

                    this.appointLocation(unoriginalDetail);

                    this.generateCommands(unoriginalDetail, false);

                    unoriginalDetail.setFactQuantity(unoriginalDetail.getFactQuantity().subtract(this.getAllocationQuantity()));
                    unoriginalDetail.setRemainderQuantity(BigDecimal.ZERO);
                }
            }

            //region 更新原始行
            originalDetail.setFactQuantity(Optional.ofNullable(this.getHeader().getDetails().stream().filter(d -> d.getParent() == originalDetail && !d.isOriginal()).map(OutboundOrderDetail::getFactQuantity).reduce(BigDecimal.ZERO, BigDecimal::add)).orElse(BigDecimal.ZERO));
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
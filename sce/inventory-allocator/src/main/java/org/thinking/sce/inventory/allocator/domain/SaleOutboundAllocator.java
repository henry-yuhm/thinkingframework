package org.thinking.sce.inventory.allocator.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.document.ShipmentOrderDetail;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.support.PackageType;
import org.thinking.sce.service.core.domain.support.StorageClassification;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOutboundAllocator extends AbstractAllocator {
    public SaleOutboundAllocator(ShipmentOrderHeader header) throws Exception {
        super(header);
    }

    @Override
    public void allocate() throws Exception {
        //校验单据
        this.verify();

        //获取原始行数据
        List<ShipmentOrderDetail> details = this.getHeader().getDetails().stream().filter(ShipmentOrderDetail::isOriginal).sorted(Comparator.comparing(ShipmentOrderDetail::getId)).collect(Collectors.toList());
        if (details == null || details.size() == 0) {
            throw CompositeException.getException("单据无明细，不能分配批号", this.getHeader(), this.getHeader().getOwner());
        }

        //分配批号
        for (ShipmentOrderDetail detail : details) {
            this.initialize(detail);

            if (detail.getCasesQuantity().compareTo(BigDecimal.ZERO) > 0) {
                this.getLots().clear();
                this.getLotInventories().clear();
                this.getInventories().clear();
                this.getCommands().clear();

                this.setPackageType(PackageType.WHOLEPIECES);
                this.setAllocationQuantity(detail.getCasesQuantity());

                this.acquireLotInventory(detail);
                this.acquireLot(false);

                this.acquireLocation(detail);

                this.generateCommands(detail, false);

                detail.setCasesQuantity(this.getAllocationQuantity());
            }

            if (detail.getRemainderQuantity().compareTo(BigDecimal.ZERO) > 0) {
                this.getLots().clear();
                this.getLotInventories().clear();
                this.getInventories().clear();
                this.getCommands().clear();

                this.setPackageType(PackageType.REMAINDER);
                this.setAllocationQuantity(detail.getRemainderQuantity());

                this.acquireLotInventory(detail);
                this.acquireLot(false);

                if (this.getAllocationQuantity().compareTo(BigDecimal.ZERO) > 0 && detail.getItem().getStorageClassification() == StorageClassification.ALL) {
                    this.setAllocationQuantity(detail.getRemainderQuantity());
                    this.acquireLot(true);
                }

                this.acquireLocation(detail);

                this.generateCommands(detail, false);

                detail.setRemainderQuantity(this.getAllocationQuantity());
            }

            detail.setLessnessQuantity(detail.getCasesQuantity().add(detail.getRemainderQuantity()));
            detail.setCasesQuantity(BigDecimal.ZERO);
            detail.setRemainderQuantity(BigDecimal.ZERO);
        }

        this.save();

        //校验指令
        this.verify();
    }
}
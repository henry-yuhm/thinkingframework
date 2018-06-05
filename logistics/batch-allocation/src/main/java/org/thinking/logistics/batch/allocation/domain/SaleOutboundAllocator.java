package org.thinking.logistics.batch.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.domain.support.StorageClassification;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOutboundAllocator extends AbstractAllocator {
    public SaleOutboundAllocator(OutboundOrderHeader header) throws Exception {
        super(header);
    }

    @Override
    public void allocate() throws Exception {
        //校验单据
        this.verify();

        //获取原始行数据
        List<OutboundOrderDetail> details = this.getHeader().getDetails().stream().filter(OutboundOrderDetail::isOriginal).sorted(Comparator.comparing(OutboundOrderDetail::getId)).collect(Collectors.toList());
        if (details == null || details.size() == 0) {
            throw CompositeException.getException("单据无明细，不能分配批号", this.getHeader(), this.getHeader().getOwner());
        }

        //分配批号
        for (OutboundOrderDetail detail : details) {
            this.initialize(detail);

            if (detail.getWholepiecesQuantity().compareTo(BigDecimal.ZERO) > 0) {
                this.getBatches().clear();
                this.getBatchInventories().clear();
                this.getInventories().clear();
                this.getCommands().clear();

                this.setPackageType(PackageType.WHOLEPIECES);
                this.setAllocationQuantity(detail.getWholepiecesQuantity());

                this.acquireBatchInventory(detail);
                this.acquireBatch(false);

                this.acquireLocation(detail);

                this.generateCommands(detail, false);

                detail.setWholepiecesQuantity(this.getAllocationQuantity());
            }

            if (detail.getRemainderQuantity().compareTo(BigDecimal.ZERO) > 0) {
                this.getBatches().clear();
                this.getBatchInventories().clear();
                this.getInventories().clear();
                this.getCommands().clear();

                this.setPackageType(PackageType.REMAINDER);
                this.setAllocationQuantity(detail.getRemainderQuantity());

                this.acquireBatchInventory(detail);
                this.acquireBatch(false);

                if (this.getAllocationQuantity().compareTo(BigDecimal.ZERO) > 0 && detail.getGoods().getStorageClassification() == StorageClassification.ALL) {
                    this.setAllocationQuantity(detail.getRemainderQuantity());
                    this.acquireBatch(true);
                }

                this.acquireLocation(detail);

                this.generateCommands(detail, false);

                detail.setRemainderQuantity(this.getAllocationQuantity());
            }

            detail.setLessnessQuantity(detail.getWholepiecesQuantity().add(detail.getRemainderQuantity()));
            detail.setWholepiecesQuantity(BigDecimal.ZERO);
            detail.setRemainderQuantity(BigDecimal.ZERO);
        }

        this.save();

        //校验指令
        this.verify();
    }
}
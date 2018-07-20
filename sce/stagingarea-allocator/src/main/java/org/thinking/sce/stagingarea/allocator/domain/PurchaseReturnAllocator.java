package org.thinking.sce.stagingarea.allocator.domain;

import lombok.*;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnAllocator extends AbstractAllocator {
    public PurchaseReturnAllocator(ShipmentOrderHeader header) {
        super(header);
    }

    @Override
    public void acquireAvailableArea() throws Exception {
        super.acquireAvailableArea();

        if (this.getStagingareas().size() == 0) {
            throw CompositeException.getException("需【" + this.getQuantity() + "】个、类型【" + this.getStagingarea().getType().name() + "】", this.getHeader(), this.getHeader().getOwner());
        }
    }
}
package org.thinking.logistics.stagingarea.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnAllocator extends AbstractAllocator {
    public PurchaseReturnAllocator(OutboundOrderHeader header) {
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
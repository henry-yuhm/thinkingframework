package org.thinking.logistics.stagingarea.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnAllocator extends AbstractAllocator {
    public PurchaseReturnAllocator(OutboundHeader header) {
        super(header);
    }

    @Override
    public void getAvailableArea() throws Exception {
        super.getAvailableArea();

        if (this.stagingareas.size() == 0) {
            throw CompositeException.getException("需【" + this.quantity + "】个、类型【" + this.stagingarea.getType().name() + "】", this.header, this.header.getOwner());
        }
    }
}
package org.thinking.logistics.stagingarea.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOutboundAllocator extends AbstractAllocator {
    public SaleOutboundAllocator(OutboundHeader header) {
        super(header);
    }

    @Override
    public void acquireVirtualConfiguration() throws Exception {
        super.acquireVirtualConfiguration(this.getHeader().getAddress().getDirection());
    }

    @Override
    public void acquireAvailableArea() throws Exception {
        //手工指定过月台的不再分配
        if (this.getHeader().getSourceStagingarea() != null) {
            this.setStagingarea(this.getHeader().getSourceStagingarea());
            this.getStagingareas().add(this.getStagingarea());
            return;
        }

        super.acquireAvailableArea();

        if (this.getStagingareas().size() == 0) {
            throw CompositeException.getException("需【" + this.getQuantity() + "】个、类型【" + this.getStagingarea().getType().name() + "】、类别【" + this.getStagingarea().getCategory().name() + "】、提货方式【" + this.getStagingarea().getTakegoodsMode().name() + "】、配送方向【" + this.getStagingarea().getDirection().getName() + "】", this.getHeader(), this.getHeader().getOwner(), this.getHeader().getCustomer());
        }
    }
}
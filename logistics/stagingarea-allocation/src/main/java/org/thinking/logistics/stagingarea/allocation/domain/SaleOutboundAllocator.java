package org.thinking.logistics.stagingarea.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOutboundAllocator extends AbstractAllocator {
    public SaleOutboundAllocator(OutboundHeader header) {
        super(header);
    }

    @Override
    public void getVirtualConfiguration() throws Exception {
        super.getVirtualConfiguration(this.header.getAddress().getDirection());
    }

    @Override
    public void getAvailableArea() throws Exception {
        //手工指定过月台的不再分配
        if (this.header.getSourceStagingarea() != null) {
            this.stagingarea = this.header.getSourceStagingarea();
            this.stagingareas.add(this.stagingarea);
            return;
        }

        super.getAvailableArea();

        if (this.stagingareas.size() == 0) {
            throw CompositeException.getException("需【" + this.quantity + "】个、类型【" + this.stagingarea.getType().name() + "】、类别【" + this.stagingarea.getCategory().name() + "】、提货方式【" + this.stagingarea.getTakegoodsMode().name() + "】、配送方向【" + this.stagingarea.getDirection().getName() + "】", this.header, this.header.getOwner(), this.header.getCustomer());
        }
    }
}
package org.thinking.logistics.order.initialization.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;
import org.thinking.logistics.services.core.domain.support.OutboundPriority;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnInitializer extends AbstractInitializer {
    public PurchaseReturnInitializer(OutboundHeader header) {
        super(header);
    }

    @Override
    public void save() throws Exception {
        this.getHeader().setPriority(OutboundPriority.PURCHASE_RETURN);
        this.getHeader().setTakegoodsMode(TakegoodsMode.NONE);
        this.getHeader().setTakegoodsModeSwitch(TakegoodsMode.NONE);

        super.save();
    }
}
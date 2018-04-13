package org.thinking.logistics.order.initialization.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.support.OutboundPriority;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnInitializer extends AbstractInitializer {
    public PurchaseReturnInitializer(OutboundHeader header) {
        super(header);
    }

    @Override
    public void initialize() throws Exception {
        super.initialize();

        this.header.setPriority(OutboundPriority.PURCHASE_RETURN);

        this.header.setTakegoodsMode(TakegoodsMode.NONE);
        this.header.setTakegoodsModeSwitch(TakegoodsMode.NONE);

        this.save();
    }
}
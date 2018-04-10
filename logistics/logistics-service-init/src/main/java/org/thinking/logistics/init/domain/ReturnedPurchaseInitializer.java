package org.thinking.logistics.init.domain;

import org.thinking.logistics.core.domain.support.OutboundPriority;
import org.thinking.logistics.core.domain.support.TakegoodsMode;
import org.thinking.logistics.core.entity.bill.OutboundHeader;

public class ReturnedPurchaseInitializer extends AbstractInitializer {
    public ReturnedPurchaseInitializer(OutboundHeader header) {
        super(header);
    }

    @Override
    public void initialize() throws Exception {
        super.initialize();

        this.header.setPriority(OutboundPriority.RETURNED_PURCHASE);

        this.header.setTakegoodsMode(TakegoodsMode.NONE);
        this.header.setTakegoodsModeSwitch(TakegoodsMode.NONE);

        this.save();
    }
}
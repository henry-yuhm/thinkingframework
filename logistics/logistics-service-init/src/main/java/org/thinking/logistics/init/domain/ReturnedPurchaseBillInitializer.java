package org.thinking.logistics.init.domain;

import org.thinking.logistics.core.entity.bill.OutboundHeader;
import org.thinking.logistics.core.entity.support.OutboundPriority;
import org.thinking.logistics.core.entity.support.TakegoodsMode;
import org.thinking.logistics.core.repository.HeaderRepository;

public class ReturnedPurchaseBillInitializer extends AbstractBillInitializer {
    public ReturnedPurchaseBillInitializer(HeaderRepository<OutboundHeader> repository, OutboundHeader header) {
        super(repository, header);
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
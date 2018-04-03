package org.jointown.logistics.init.domain;

import org.jointown.logistics.core.entity.bill.OutboundHeader;
import org.jointown.logistics.core.entity.support.OutboundPriority;
import org.jointown.logistics.core.entity.support.TakegoodsMode;
import org.jointown.logistics.core.repository.HeaderRepository;

public class ReturnedPurchaseBillInitializer extends AbstractBillInitializer {
    public ReturnedPurchaseBillInitializer(HeaderRepository<OutboundHeader> repository, long id) {
        super(repository, id);
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
package org.jointown.logistics.init.domain;

import org.jointown.logistics.core.entity.bill.PurchaseOutboundHeader;
import org.jointown.logistics.core.entity.support.OutboundPriority;
import org.jointown.logistics.core.repository.HeaderRepository;

public class PurchaseReturnBillInitializer extends AbstractBillInitializer<PurchaseOutboundHeader> {
    public PurchaseReturnBillInitializer(HeaderRepository<PurchaseOutboundHeader> repository, long id) {
        super(repository, repository.findOne(id));
    }

    @Override
    public void initialize() throws Exception {
        super.initialize();

        this.getHeader().setPriority(OutboundPriority.PURCHASE_RETURN);

        this.getRepository().save(this.getHeader());
    }
}
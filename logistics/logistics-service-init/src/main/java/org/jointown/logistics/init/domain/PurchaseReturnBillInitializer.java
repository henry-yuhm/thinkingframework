package org.jointown.logistics.init.domain;

import org.jointown.logistics.core.entity.bill.PurchaseReturnBillHeader;
import org.jointown.logistics.core.entity.support.BillPriority;
import org.jointown.logistics.core.repository.BillHeaderRepository;

public class PurchaseReturnBillInitializer extends AbstractBillInitializer<PurchaseReturnBillHeader> {
    public PurchaseReturnBillInitializer(BillHeaderRepository<PurchaseReturnBillHeader> repository, long id) {
        super(repository, repository.findOne(id));
    }

    @Override
    public void initialize() throws Exception {
        super.initialize();

        this.getHeader().setPriority(BillPriority.PURCHASE_RETURN);

        this.getRepository().save(this.getHeader());
    }
}
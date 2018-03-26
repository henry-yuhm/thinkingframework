package org.jointown.logistics.common.entity.bill;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class PurchaseReturnBillDetail extends OutboundBillDetail {
    @OneToOne(fetch = FetchType.LAZY)
    private PurchaseReturnBillDetail parent;//父单据明细

    @OneToOne(fetch = FetchType.LAZY)
    private PurchaseReturnBillHeader billHeader;//单据抬头
}
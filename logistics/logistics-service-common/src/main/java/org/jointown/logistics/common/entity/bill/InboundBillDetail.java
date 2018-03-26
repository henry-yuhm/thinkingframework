package org.jointown.logistics.common.entity.bill;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class InboundBillDetail extends BillDetail {
    @OneToOne(fetch = FetchType.LAZY)
    private InboundBillDetail parent;//父单据明细

    @OneToOne(fetch = FetchType.LAZY)
    private InboundBillHeader billHeader;//单据抬头
}
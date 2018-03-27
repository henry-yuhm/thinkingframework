package org.jointown.logistics.core.entity.bill;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class InboundBillDetail extends BillDetail {
    @OneToOne(fetch = FetchType.LAZY)
    private InboundBillDetail parent;//父单据明细

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private InboundBillHeader header;//单据抬头
}
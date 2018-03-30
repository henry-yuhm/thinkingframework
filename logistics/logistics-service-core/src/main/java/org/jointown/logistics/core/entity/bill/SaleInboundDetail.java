package org.jointown.logistics.core.entity.bill;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class SaleInboundDetail extends InboundDetail {
    @OneToOne(fetch = FetchType.LAZY)
    private SaleInboundDetail parent;//父单据明细

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private SaleInboundHeader header;//单据抬头
}
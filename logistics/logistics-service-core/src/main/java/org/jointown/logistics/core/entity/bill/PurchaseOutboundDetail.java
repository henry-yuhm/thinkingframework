package org.jointown.logistics.core.entity.bill;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class PurchaseOutboundDetail extends OutboundDetail {
    @OneToOne(fetch = FetchType.LAZY)
    private PurchaseOutboundDetail parent;//父单据明细

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOutboundHeader header;//单据抬头

    public PurchaseOutboundDetail getParent() {
        return parent;
    }

    public void setParent(PurchaseOutboundDetail parent) {
        this.parent = parent;
    }

    public PurchaseOutboundHeader getHeader() {
        return header;
    }

    public void setHeader(PurchaseOutboundHeader header) {
        this.header = header;
    }
}
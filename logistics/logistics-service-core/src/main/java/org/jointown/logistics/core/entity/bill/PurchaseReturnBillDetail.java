package org.jointown.logistics.core.entity.bill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class PurchaseReturnBillDetail extends OutboundBillDetail {
    @OneToOne(fetch = FetchType.LAZY)
    private PurchaseReturnBillDetail parent;//父单据明细

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private PurchaseReturnBillHeader header;//单据抬头

    public PurchaseReturnBillDetail getParent() {
        return parent;
    }

    public void setParent(PurchaseReturnBillDetail parent) {
        this.parent = parent;
    }

    public PurchaseReturnBillHeader getHeader() {
        return header;
    }

    public void setHeader(PurchaseReturnBillHeader header) {
        this.header = header;
    }
}
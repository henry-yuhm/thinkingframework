package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.StagingArea;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
public class PurchaseReturnBillHeader extends OutboundBillHeader {
    @OneToOne(fetch = FetchType.LAZY)
    private StagingArea stagingArea;//月台

    @Column(nullable = false)
    private BigInteger printTimes;//打印次数

    @OneToMany
    private Set<PurchaseReturnBillDetail> details;//单据明细

    public StagingArea getStagingArea() {
        return stagingArea;
    }

    public void setStagingArea(StagingArea stagingArea) {
        this.stagingArea = stagingArea;
    }

    public BigInteger getPrintTimes() {
        return printTimes;
    }

    public void setPrintTimes(BigInteger printTimes) {
        this.printTimes = printTimes;
    }

    public Set<PurchaseReturnBillDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<PurchaseReturnBillDetail> details) {
        this.details = details;
    }
}
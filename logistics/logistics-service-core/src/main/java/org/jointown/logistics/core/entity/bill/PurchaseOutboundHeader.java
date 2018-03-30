package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.Stagingarea;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
public class PurchaseOutboundHeader extends OutboundHeader {
    @OneToOne(fetch = FetchType.LAZY)
    private Stagingarea stagingarea;//月台

    @Column(nullable = false)
    private BigInteger printTimes;//打印次数

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "header_id")}, inverseJoinColumns = {@JoinColumn(name = "detail_id")})
    private Set<PurchaseOutboundDetail> details;//单据明细

    public Stagingarea getStagingarea() {
        return stagingarea;
    }

    public void setStagingarea(Stagingarea stagingarea) {
        this.stagingarea = stagingarea;
    }

    public BigInteger getPrintTimes() {
        return printTimes;
    }

    public void setPrintTimes(BigInteger printTimes) {
        this.printTimes = printTimes;
    }

    public Set<PurchaseOutboundDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<PurchaseOutboundDetail> details) {
        this.details = details;
    }
}
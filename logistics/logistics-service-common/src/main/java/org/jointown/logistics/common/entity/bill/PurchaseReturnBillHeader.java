package org.jointown.logistics.common.entity.bill;

import org.jointown.logistics.common.entity.StagingArea;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.math.BigInteger;
import java.util.Set;

@Entity
public class PurchaseReturnBillHeader extends OutboundBillHeader {
    @OneToOne(fetch = FetchType.LAZY)
    private StagingArea stagingArea;//月台

    private BigInteger printTimes;//打印次数

    @ManyToMany
    private Set<PurchaseReturnBillDetail> billDetails;//单据明细
}
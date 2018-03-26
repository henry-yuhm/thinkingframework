package org.jointown.logistics.common.entity.bill;

import org.jointown.logistics.common.entity.support.BatchNumberRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class OutboundSaleBillDetail extends OutboundBillDetail {
    @OneToOne(fetch = FetchType.LAZY)
    private OutboundSaleBillDetail parent;//父单据明细

    @OneToOne(fetch = FetchType.LAZY)
    private OutboundSaleBillHeader billHeader;//单据抬头

    private BatchNumberRequest batchNumberRequest;//批号要求

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal stocklessnessQuantity;//库存不足数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal price;//单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal amount;//金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal taxTicketAmount;//税票金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settleupPrice;//结算单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settleupAmount;//结算金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal flittingCostPrice;//调拨成本价
}
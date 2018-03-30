package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.support.BatchRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class SaleOrderDetail extends OrderDetail {
    @OneToOne(fetch = FetchType.LAZY)
    private SaleOrderDetail parent;//父单据明细

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private SaleOrderHeader header;//单据抬头

    private BatchRequest batchRequest;//批号要求

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal price;//单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal amount;//金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal taxes;//税票金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settlementPrice;//结算单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settlementAmount;//结算金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal flittingCostPrice;//调拨成本价
}
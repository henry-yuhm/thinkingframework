package org.jointown.logistics.core.entity.bill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class SaleOutboundDetail extends OutboundDetail {
    @OneToOne(fetch = FetchType.LAZY)
    private SaleOutboundDetail parent;//父单据明细

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private SaleOutboundHeader header;//单据抬头

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private SaleOrderDetail orderDetail;//订单明细

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal lessnessQuantity;//库存不足数量
}
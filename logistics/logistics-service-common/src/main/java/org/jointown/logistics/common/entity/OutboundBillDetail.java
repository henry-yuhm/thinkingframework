package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.BatchNumberRequest;
import org.jointown.logistics.common.entity.support.StockStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "parentBillDetail", foreignKey = @ForeignKey(name = "fk_obd_parent_bd"))
})
public class OutboundBillDetail extends BillDetail {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obd_location"))
    private Location location;//货位

    private StockStatus stockStatus;//库存状态

    private BatchNumberRequest batchNumberRequest;//批号要求

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plannedQuantity;//计划数量

    @Column(nullable = false)
    private BigInteger plannedPieces = BigInteger.ZERO;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plannedRemainder;//计划余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal actualQuantity;//实际数量

    @Column(nullable = false)
    private BigInteger actualPieces = BigInteger.ZERO;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal actualRemainder;//实际余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal wholeQuantity;//整件未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainderQuantity;//零货未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal stocklessnessQuantity;//库存不足数量

    private boolean original;//是否原始数据

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
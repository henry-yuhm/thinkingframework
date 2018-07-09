package org.thinking.logistics.services.core.domain.document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.common.Location;
import org.thinking.logistics.services.core.domain.support.InventoryState;
import org.thinking.logistics.services.core.domain.support.LotRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ShipmentOrderDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY)
    private ShipmentOrderDetail parent;//父明细

    @Column(nullable = false)
    private boolean original = true;//是否原始明细

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private ShipmentOrderHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState inventoryState = InventoryState.QUALIFICATION;//库存状态

    private LotRequest request;//批号要求

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal expectedQuantity;//计划数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal expectedPieces;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal expectedRemainder;//计划余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal actualQuantity;//实际数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal actualPieces;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal actualRemainder;//实际余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal wholepiecesQuantity;//整件未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainderQuantity;//零货未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal lessnessQuantity;//库存不足数量

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

    public BigDecimal getExpectedPieces() {
        return this.getItem().getPieces(expectedQuantity);
    }

    public BigDecimal getExpectedRemainder() {
        return this.getItem().getRemainder(expectedQuantity);
    }

    public BigDecimal getActualPieces() {
        return this.getItem().getPieces(actualQuantity);
    }

    public BigDecimal getActualRemainder() {
        return this.getItem().getRemainder(actualQuantity);
    }
}
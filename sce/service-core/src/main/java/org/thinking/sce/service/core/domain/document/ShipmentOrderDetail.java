package org.thinking.sce.service.core.domain.document;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.common.Location;
import org.thinking.sce.service.core.domain.support.*;

import javax.persistence.*;
import javax.persistence.Entity;
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
    private boolean original;//是否原始明细

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState inventoryState;//库存状态

    private LotRequest request;//批号要求

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal expectedQuantity;//计划数量

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal expectedCases;//计划件数

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal expectedRemainder;//计划余数

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal actualQuantity;//实际数量

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal actualCases;//实际件数

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal actualRemainder;//实际余数

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal casesQuantity;//整件未处理数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal remainderQuantity;//零货未处理数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal lessnessQuantity;//库存不足数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal price;//单价

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal amount;//金额

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal taxes;//税票金额

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal settlementPrice;//结算单价

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal settlementAmount;//结算金额

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal flittingCostPrice;//调拨成本价

    public BigDecimal getExpectedCases() {
        return this.getItem().getCases(expectedQuantity);
    }

    public BigDecimal getExpectedRemainder() {
        return this.getItem().getRemainder(expectedQuantity);
    }

    public BigDecimal getActualCases() {
        return this.getItem().getCases(actualQuantity);
    }

    public BigDecimal getActualRemainder() {
        return this.getItem().getRemainder(actualQuantity);
    }
}
package org.thinking.logistics.services.core.domain.document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.thinking.logistics.services.core.domain.core.Location;
import org.thinking.logistics.services.core.domain.support.InventoryState;
import org.thinking.logistics.services.core.domain.support.LotRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
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
    private BigDecimal planQuantity = BigDecimal.ZERO;//计划数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal planPieces = BigDecimal.ZERO;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal planRemainder = BigDecimal.ZERO;//计划余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal factPieces = BigDecimal.ZERO;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal factRemainder = BigDecimal.ZERO;//实际余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal wholepiecesQuantity = BigDecimal.ZERO;//整件未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainderQuantity = BigDecimal.ZERO;//零货未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal lessnessQuantity = BigDecimal.ZERO;//库存不足数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal price = BigDecimal.ZERO;//单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal amount = BigDecimal.ZERO;//金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal taxes = BigDecimal.ZERO;//税票金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settlementPrice = BigDecimal.ZERO;//结算单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settlementAmount = BigDecimal.ZERO;//结算金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal flittingCostPrice = BigDecimal.ZERO;//调拨成本价

    public BigDecimal getPlanPieces() {
        return this.getItem().getPieces(planQuantity);
    }

    public BigDecimal getPlanRemainder() {
        return this.getItem().getRemainder(planQuantity);
    }

    public BigDecimal getFactPieces() {
        return this.getItem().getPieces(factQuantity);
    }

    public BigDecimal getFactRemainder() {
        return this.getItem().getRemainder(factQuantity);
    }
}
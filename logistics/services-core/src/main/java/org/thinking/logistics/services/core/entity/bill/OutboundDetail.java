package org.thinking.logistics.services.core.entity.bill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.support.BatchesRequest;
import org.thinking.logistics.services.core.domain.support.InventoryState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class OutboundDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY)
    private OutboundDetail parent;//父明细

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private OutboundHeader header;//抬头

    @Column(nullable = false)
    private InventoryState inventoryState = InventoryState.QUALIFICATION;//库存状态

    private BatchesRequest request = BatchesRequest.NO_DEMAND;//批号要求

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planQuantity = BigDecimal.ZERO;//计划数量

    @Column(nullable = false)
    private int planPieces = 0;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planRemainder = BigDecimal.ZERO;//计划余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false)
    private int factPieces = 0;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factRemainder = BigDecimal.ZERO;//实际余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal wholeQuantity = BigDecimal.ZERO;//整件未处理数量

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

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "detail_id"), inverseJoinColumns = @JoinColumn(name = "supplement_id"))
    private Set<SupplementDetail> details = new LinkedHashSet<>();//增补明细
}
package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.Quantity;
import org.jointown.logistics.common.entity.support.StockStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Stock {
    @Id
    @TableGenerator(name = "StockId", table = "StockId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "StockId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_stk_wh"))
    private Warehouse warehouse;//仓库

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_stk_own"))
    private Owner owner;//业主

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_stk_gds"))
    private Goods goods;//商品

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_stk_btn"))
    private BatchNumber batchNumber;//批号

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_stk_loc"))
    private Location location;//货位

    private StockStatus stockStatus;//库存状态

    @AttributeOverrides(value = {
            @AttributeOverride(name = "quantity", column = @Column(name = "quantity", nullable = false, precision = 12, scale = 5)),
            @AttributeOverride(name = "pieces", column = @Column(name = "pieces", nullable = false)),
            @AttributeOverride(name = "remainder", column = @Column(name = "remainder", nullable = false, precision = 12, scale = 5))
    })
    private Quantity quantity;//数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal inboundPlusQuantity;//入库预占数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal outboundMinusQuantity;//出库预扣数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal replenishmentPlusQuantity;//补货预占数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal replenishmentMinusQuantity;//补货预扣数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal transitionQuantity;//在途数量

    private boolean locking;//是否锁定

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal lockingQuantity;//锁定数量

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_stk_plt"))
    private Pallet pallet;//托盘

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableOutboundQuantity;//可用出库数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal physicalOutboundQuantity;//物理出库数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableGrossQuantity;//可用总数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableQuantity;//可用数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableObtainQuantity;//可用索取数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableReplenishmentQuantity;//可用补货数量
}
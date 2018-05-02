package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import org.thinking.logistics.services.core.domain.*;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.support.InventoryState;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Inventory {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batches batches;//批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState inventoryState;//库存状态

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    @Column(nullable = false, precision = 12)
    private BigDecimal pieces = BigDecimal.ZERO;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainder = BigDecimal.ZERO;//余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal inboundQuantity = BigDecimal.ZERO;//入库预占数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal outboundQuantity = BigDecimal.ZERO;//出库预扣数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plusQuantity = BigDecimal.ZERO;//补货预占数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal minusQuantity = BigDecimal.ZERO;//补货预扣数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal transitionalQuantity = BigDecimal.ZERO;//在途数量

    @Column(nullable = false)
    private boolean locking = false;//锁定

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal lockingQuantity = BigDecimal.ZERO;//锁定数量

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @Transient
    private BigDecimal availableOutboundQuantity;//可用出库数量

    @Transient
    private BigDecimal physicalOutboundQuantity;//物理出库数量

    @Transient
    private BigDecimal availableGrossQuantity;//可用总数量

    @Transient
    private BigDecimal availableQuantity;//可用数量

    @Transient
    private BigDecimal availableObtainQuantity;//可用索取数量

    @Transient
    private BigDecimal availableReplenishingQuantity;//可用补货数量

    public BigDecimal getAvailableOutboundQuantity() {
        return quantity.subtract(outboundQuantity).subtract(minusQuantity).add(plusQuantity).subtract(lockingQuantity);
    }

    public BigDecimal getPhysicalOutboundQuantity() {
        return quantity.subtract(outboundQuantity).subtract(lockingQuantity).max(BigDecimal.ZERO);
    }
}
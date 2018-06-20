package org.thinking.logistics.services.core.domain.inventory;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.core.*;
import org.thinking.logistics.services.core.domain.support.InventoryState;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Inventory extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lot lot;//批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState inventoryState;//库存状态

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal pieces = BigDecimal.ZERO;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal remainder = BigDecimal.ZERO;//余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal inboundQuantity = BigDecimal.ZERO;//入库数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal outboundQuantity = BigDecimal.ZERO;//出库数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal replenishedFromQuantity = BigDecimal.ZERO;//补出数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal replenishedToQuantity = BigDecimal.ZERO;//补入数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal transferredFromQuantity = BigDecimal.ZERO;//移出数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal transferredToQuantity = BigDecimal.ZERO;//移入数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal transitionalQuantity = BigDecimal.ZERO;//在途数量

    @Column(nullable = false)
    private boolean locking = false;//锁定

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal lockingQuantity = BigDecimal.ZERO;//锁定数量

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    //    @Transient
    private BigDecimal availableOutboundQuantity;//可用出库数量

    //    @Transient
    private BigDecimal physicalOutboundQuantity;//物理出库数量

    @Transient
    private BigDecimal availableGrossQuantity;//可用总数量

    @Transient
    private BigDecimal availableQuantity;//可用数量

    @Transient
    private BigDecimal availableObtainQuantity;//可用索取数量

    @Transient
    private BigDecimal availableReplenishingQuantity;//可用补货数量

    public BigDecimal getPieces() {
        return goods.getPieces(quantity);
    }

    public BigDecimal getRemainder() {
        return goods.getRemainder(quantity);
    }

    public BigDecimal getAvailableOutboundQuantity() {
        return quantity.subtract(outboundQuantity).subtract(replenishedFromQuantity).add(replenishedToQuantity).subtract(lockingQuantity);
    }

    public BigDecimal getPhysicalOutboundQuantity() {
        return quantity.subtract(outboundQuantity).subtract(lockingQuantity).max(BigDecimal.ZERO);
    }
}
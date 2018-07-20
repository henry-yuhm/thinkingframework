package org.thinking.sce.service.core.domain.inventory;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.*;
import org.thinking.sce.service.core.domain.container.Pallet;
import org.thinking.sce.service.core.domain.support.InventoryState;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Inventory extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;//商品

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lot lot;//批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState inventoryState;//库存状态

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal quantity;//数量

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal cases;//件数

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal remainder;//余数

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal inboundQuantity;//入库数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal outboundQuantity;//出库数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal replenishedFromQuantity;//补出数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal replenishedToQuantity;//补入数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal movingFromQuantity;//移出数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal movingToQuantity;//移入数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal transitionalQuantity;//在途数量

    @Column(nullable = false)
    private boolean locking;//锁定

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal lockingQuantity;//锁定数量

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
    private BigDecimal availableReplenishmentQuantity;//可用补货数量

    public BigDecimal getCases() {
        return item.getCases(quantity);
    }

    public BigDecimal getRemainder() {
        return item.getRemainder(quantity);
    }

    public BigDecimal getAvailableOutboundQuantity() {
        return quantity.subtract(outboundQuantity).subtract(replenishedFromQuantity).add(replenishedToQuantity).subtract(lockingQuantity);
    }

    public BigDecimal getPhysicalOutboundQuantity() {
        return quantity.subtract(outboundQuantity).subtract(lockingQuantity).max(BigDecimal.ZERO);
    }
}
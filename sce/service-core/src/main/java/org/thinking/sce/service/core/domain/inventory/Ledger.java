package org.thinking.sce.service.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.Item;
import org.thinking.sce.service.core.domain.common.Location;
import org.thinking.sce.service.core.domain.common.Lot;
import org.thinking.sce.service.core.domain.common.Owner;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.container.Pallet;
import org.thinking.sce.service.core.domain.document.Header;
import org.thinking.sce.service.core.domain.support.InventoryState;
import org.thinking.sce.service.core.domain.support.LedgerCategory;
import org.thinking.sce.service.core.domain.support.LedgerSummary;
import org.thinking.sce.service.core.domain.support.LedgerType;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Ledger extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private LedgerSummary summary;//账页摘要

    @Column(nullable = false)
    private LedgerType type;//账页类型

    @Column(nullable = false)
    private LedgerCategory category;//账页类别

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal quantity;//数量

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

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal lockingQuantity;//锁定数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal balance;//结存

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal grossBalance;//总结存

    public abstract void setHeader(Header header);
}
package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import org.thinking.logistics.services.core.domain.*;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.support.InventoryState;
import org.thinking.logistics.services.core.domain.support.LedgerCategory;
import org.thinking.logistics.services.core.domain.support.LedgerSummary;
import org.thinking.logistics.services.core.domain.support.LedgerType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Ledger {
    @Id
    @GeneratedValue
    private long id;

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
    private Goods goods;//商品

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batches batches;//批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState inventoryState;//库存状态

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity = BigDecimal.ZERO;//数量

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

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal lockingQuantity = BigDecimal.ZERO;//锁定数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal balance = BigDecimal.ZERO;//结存

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal grossBalance = BigDecimal.ZERO;//总结存

    @Column(nullable = false)
    private Date creationTime = Date.valueOf(LocalDate.now());//创建时间
}
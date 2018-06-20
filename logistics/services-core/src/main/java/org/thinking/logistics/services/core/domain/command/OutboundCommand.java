package org.thinking.logistics.services.core.domain.command;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.thinking.logistics.services.core.domain.barcode.OutboundBarcode;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.core.Location;
import org.thinking.logistics.services.core.domain.core.Lot;
import org.thinking.logistics.services.core.domain.core.Platform;
import org.thinking.logistics.services.core.domain.core.Task;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.support.AppendantSign;
import org.thinking.logistics.services.core.domain.support.InventoryState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class OutboundCommand extends Command {
    @ManyToOne(fetch = FetchType.LAZY)
    private OutboundCommand parent;//父指令

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundOrderHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundOrderDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lot lot;//批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState inventoryState;//库存状态

    @Column(nullable = false)
    private boolean activated = false;//激活

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creationQuantity = BigDecimal.ZERO;//创建数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal creationPieces = BigDecimal.ZERO;//创建件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal creationRemainder = BigDecimal.ZERO;//创建余数

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;//作业任务

    @ManyToOne(fetch = FetchType.LAZY)
    private OutboundBarcode barcode;//作业条码

    @Column(nullable = false)
    private AppendantSign appendantSign = AppendantSign.ORIGINAL;//追加标识

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Platform platform;//站台

    @Column(nullable = false)
    private String pickingOrder = "0";//拣货顺序

    private BigDecimal remainder = BigDecimal.ZERO;//余量

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "command_id"), inverseJoinColumns = @JoinColumn(name = "rep_command_id"))
    private Set<ReplenishingCommand> commands = new LinkedHashSet<>();//补货指令

    public BigDecimal getCreationPieces() {
        return getGoods().getPieces(creationQuantity);
    }

    public BigDecimal getCreationRemainder() {
        return getGoods().getRemainder(creationQuantity);
    }

    public BigDecimal getPlanPieces() {
        return getGoods().getPieces(planQuantity);
    }

    public BigDecimal getPlanRemainder() {
        return getGoods().getRemainder(planQuantity);
    }

    public BigDecimal getFactPieces() {
        return getGoods().getPieces(factQuantity);
    }

    public BigDecimal getFactRemainder() {
        return getGoods().getRemainder(factQuantity);
    }
}
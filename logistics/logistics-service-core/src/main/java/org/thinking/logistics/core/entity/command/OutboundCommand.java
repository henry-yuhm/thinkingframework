package org.thinking.logistics.core.entity.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.domain.support.AppendantSign;
import org.thinking.logistics.core.domain.support.InventoryState;
import org.thinking.logistics.core.entity.Batch;
import org.thinking.logistics.core.entity.Location;
import org.thinking.logistics.core.entity.bill.OutboundDetail;
import org.thinking.logistics.core.entity.bill.OutboundHeader;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class OutboundCommand extends Command {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batch batch;//批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState state;//库存状态

    @Column(nullable = false)
    private boolean activated = false;//是否激活

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creationQuantity = BigDecimal.ZERO;//创建数量

    @Column(nullable = false)
    private int creationPieces = 0;//创建件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creationRemainder = BigDecimal.ZERO;//创建余数

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

    @Column(nullable = false)
    private AppendantSign appendantSign = AppendantSign.ORIGINAL;//追加标识

    @Column(nullable = false)
    private String pickingOrder = "0";//拣货顺序

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "command_id"), inverseJoinColumns = @JoinColumn(name = "rep_command_id"))
    private Set<ReplenishingCommand> commands = new LinkedHashSet<>();//补货指令
}
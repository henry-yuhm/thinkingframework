package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.Quantity;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
public abstract class OutboundCommand extends Command {
    @OneToOne(fetch = FetchType.LAZY)
    protected Location location;//货位

    protected Stock.StockStatus stockStatus;//库存状态

    protected boolean active;//是否激活

    @AttributeOverrides(value = {
            @AttributeOverride(name = "quantity", column = @Column(name = "creationQuantity", nullable = false, precision = 12, scale = 5)),
            @AttributeOverride(name = "pieces", column = @Column(name = "creationPieces", nullable = false)),
            @AttributeOverride(name = "remainder", column = @Column(name = "creationRemainder", nullable = false, precision = 12, scale = 5))
    })
    protected Quantity creationQuantity;//创建数量

    @AttributeOverrides(value = {
            @AttributeOverride(name = "quantity", column = @Column(name = "plannedQuantity", nullable = false, precision = 12, scale = 5)),
            @AttributeOverride(name = "pieces", column = @Column(name = "plannedPieces", nullable = false)),
            @AttributeOverride(name = "remainder", column = @Column(name = "plannedRemainder", nullable = false, precision = 12, scale = 5))
    })
    protected Quantity plannedQuantity;//计划数量

    @AttributeOverrides(value = {
            @AttributeOverride(name = "quantity", column = @Column(name = "actualQuantity", nullable = false, precision = 12, scale = 5)),
            @AttributeOverride(name = "pieces", column = @Column(name = "actualPieces", nullable = false)),
            @AttributeOverride(name = "remainder", column = @Column(name = "actualRemainder", nullable = false, precision = 12, scale = 5))
    })
    protected Quantity actualQuantity;//实际数量

    @OneToOne(fetch = FetchType.LAZY)
    protected TaskBill taskBill;//任务单

    protected AppendixSign appendixSign;//追加标识

    protected String pickingOrder;//拣货顺序

    @ManyToMany
    protected Set<TransferCommand> transferCommands;//补货指令
}
package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

@MappedSuperclass
public abstract class OutboundCommand extends Command {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obc_location"))
    private Location location;//货位

    private Stock.StockStatus stockStatus;//库存状态

    private boolean active;//是否激活

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creativeQuantity;//创建数量

    @Column(nullable = false)
    private BigInteger creativePieces = BigInteger.ZERO;//创建件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creativeRemainder;//创建余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plannedQuantity;//计划数量

    @Column(nullable = false)
    private BigInteger plannedPieces = BigInteger.ZERO;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plannedRemainder;//计划余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal actualQuantity;//实际数量

    @Column(nullable = false)
    private BigInteger actualPieces = BigInteger.ZERO;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal actualRemainder;//实际余数

    private TaskBill taskBill;//任务单

    private AppendixSign appendixSign;//追加标识

    private String pickingOrder;//拣货顺序

    @ManyToMany
    private Set<TransferCommand> transferCommands;//补货指令
}
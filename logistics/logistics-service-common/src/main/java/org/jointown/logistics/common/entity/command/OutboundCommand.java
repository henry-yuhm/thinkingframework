package org.jointown.logistics.common.entity.command;

import org.jointown.logistics.common.entity.Location;
import org.jointown.logistics.common.entity.bill.OutboundSaleBillDetail;
import org.jointown.logistics.common.entity.bill.OutboundSaleBillHeader;
import org.jointown.logistics.common.entity.bill.TaskBill;
import org.jointown.logistics.common.entity.support.AppendixSign;
import org.jointown.logistics.common.entity.support.Quantity;
import org.jointown.logistics.common.entity.support.StockStatus;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
public abstract class OutboundCommand extends Command {
    @OneToOne(fetch = FetchType.LAZY)
    private OutboundSaleBillHeader billHeader;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    private OutboundSaleBillDetail billDetail;//单据明细

    @OneToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    private StockStatus stockStatus;//库存状态

    private boolean active;//是否激活

    @AttributeOverrides(value = {
            @AttributeOverride(name = "quantity", column = @Column(name = "creationQuantity", nullable = false, precision = 12, scale = 5)),
            @AttributeOverride(name = "pieces", column = @Column(name = "creationPieces", nullable = false)),
            @AttributeOverride(name = "remainder", column = @Column(name = "creationRemainder", nullable = false, precision = 12, scale = 5))
    })
    private Quantity creationQuantity;//创建数量

    @AttributeOverrides(value = {
            @AttributeOverride(name = "quantity", column = @Column(name = "plannedQuantity", nullable = false, precision = 12, scale = 5)),
            @AttributeOverride(name = "pieces", column = @Column(name = "plannedPieces", nullable = false)),
            @AttributeOverride(name = "remainder", column = @Column(name = "plannedRemainder", nullable = false, precision = 12, scale = 5))
    })
    private Quantity plannedQuantity;//计划数量

    @AttributeOverrides(value = {
            @AttributeOverride(name = "quantity", column = @Column(name = "actualQuantity", nullable = false, precision = 12, scale = 5)),
            @AttributeOverride(name = "pieces", column = @Column(name = "actualPieces", nullable = false)),
            @AttributeOverride(name = "remainder", column = @Column(name = "actualRemainder", nullable = false, precision = 12, scale = 5))
    })
    private Quantity actualQuantity;//实际数量

    @OneToOne(fetch = FetchType.LAZY)
    private TaskBill taskBill;//任务单

    private AppendixSign appendixSign;//追加标识

    private String pickingOrder;//拣货顺序

    @ManyToMany
    private Set<TransitionCommand> commands;//补货指令
}
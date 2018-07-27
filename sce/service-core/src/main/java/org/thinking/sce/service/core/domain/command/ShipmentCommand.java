package org.thinking.sce.service.core.domain.command;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.barcode.ShipmentBarcode;
import org.thinking.sce.service.core.domain.common.Location;
import org.thinking.sce.service.core.domain.common.Lot;
import org.thinking.sce.service.core.domain.common.Platform;
import org.thinking.sce.service.core.domain.container.Pallet;
import org.thinking.sce.service.core.domain.document.PickingList;
import org.thinking.sce.service.core.domain.document.ShipmentOrderDetail;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.support.AppendantSign;
import org.thinking.sce.service.core.domain.support.InventoryState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ShipmentCommand extends Command {
    @ManyToOne(fetch = FetchType.LAZY)
    private ShipmentCommand parent;//父指令

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ShipmentOrderHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ShipmentOrderDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lot lot;//批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState inventoryState;//库存状态

    @Column(nullable = false)
    private boolean activated;//激活

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal createdQuantity;//创建数量

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal createdCases;//创建件数

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal createdRemainder;//创建余数

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal expectedQuantity;//计划数量

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal expectedCases;//计划件数

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal expectedRemainder;//计划余数

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal actualQuantity;//实际数量

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal actualCases;//实际件数

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal actualRemainder;//实际余数

    @ManyToOne(fetch = FetchType.LAZY)
    private PickingList pickingList;//作业任务

    @ManyToOne(fetch = FetchType.LAZY)
    private ShipmentBarcode shipmentBarcode;//作业条码

    @Column(nullable = false)
    private AppendantSign appendantSign;//追加标识

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Platform platform;//站台

    @Column(nullable = false, length = 100)
    private String pickingOrder;//拣货顺序

    private BigDecimal remainder;//余量

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "command_id"), inverseJoinColumns = @JoinColumn(name = "rep_command_id"))
    private Set<ReplenishmentCommand> commands;//补货指令

    public BigDecimal getCreatedCases() {
        return this.getItem().getCases(createdQuantity);
    }

    public BigDecimal getCreatedRemainder() {
        return this.getItem().getRemainder(createdQuantity);
    }

    public BigDecimal getExpectedCases() {
        return this.getItem().getCases(expectedQuantity);
    }

    public BigDecimal getExpectedRemainder() {
        return this.getItem().getRemainder(expectedQuantity);
    }

    public BigDecimal getActualCases() {
        return this.getItem().getCases(actualQuantity);
    }

    public BigDecimal getActualRemainder() {
        return this.getItem().getRemainder(actualQuantity);
    }
}
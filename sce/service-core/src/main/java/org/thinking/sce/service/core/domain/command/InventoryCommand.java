package org.thinking.sce.service.core.domain.command;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.common.Lot;
import org.thinking.sce.service.core.domain.container.Pallet;
import org.thinking.sce.service.core.domain.document.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class InventoryCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CheckListHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CheckListDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lot sourceLot;//源批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lot targetLot;//目标批号

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet sourcePallet;//源托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet targetPallet;//目标托盘

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal inventoryQuantity;//库存数量

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal inventoryCases;//库存件数

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal inventoryRemainder;//库存余数

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal actualQuantity;//实际数量

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal actualCases;//实际件数

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal actualRemainder;//实际余数

    public BigDecimal getInventoryCases() {
        return this.getItem().getCases(inventoryQuantity);
    }

    public BigDecimal getInventoryRemainder() {
        return this.getItem().getRemainder(inventoryQuantity);
    }

    public BigDecimal getActualCases() {
        return this.getItem().getCases(actualQuantity);
    }

    public BigDecimal getActualRemainder() {
        return this.getItem().getRemainder(actualQuantity);
    }
}
package org.thinking.logistics.services.core.domain.command;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.core.Lot;
import org.thinking.logistics.services.core.domain.document.InventoryDocumentDetail;
import org.thinking.logistics.services.core.domain.document.InventoryDocumentHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class InventoryCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InventoryDocumentHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InventoryDocumentDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lot sourceLot;//源批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lot targetLot;//目标批号

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet sourcePallet;//源托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet targetPallet;//目标托盘

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal inventoryQuantity = BigDecimal.ZERO;//库存数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal inventoryPieces = BigDecimal.ZERO;//库存件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal inventoryRemainder = BigDecimal.ZERO;//库存余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal factPieces = BigDecimal.ZERO;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal factRemainder = BigDecimal.ZERO;//实际余数

    public BigDecimal getInventoryPieces() {
        return this.getItem().getPieces(inventoryQuantity);
    }

    public BigDecimal getInventoryRemainder() {
        return this.getItem().getRemainder(inventoryQuantity);
    }

    public BigDecimal getFactPieces() {
        return this.getItem().getPieces(factQuantity);
    }

    public BigDecimal getFactRemainder() {
        return this.getItem().getRemainder(factQuantity);
    }
}
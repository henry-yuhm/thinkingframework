package org.thinking.logistics.services.core.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.Batches;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.documents.InventoryDocumentsDetail;
import org.thinking.logistics.services.core.domain.documents.InventoryDocumentsHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class InventoryCheckCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InventoryDocumentsHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InventoryDocumentsDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batches sourceBatches;//源批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batches targetBatches;//目标批号

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet sourcePallet;//源托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet targetPallet;//目标托盘

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal inventoryQuantity = BigDecimal.ZERO;//库存数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal inventoryPieces = BigDecimal.ZERO;//库存件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal inventoryRemainder = BigDecimal.ZERO;//库存余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factPieces = BigDecimal.ZERO;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factRemainder = BigDecimal.ZERO;//实际余数
}
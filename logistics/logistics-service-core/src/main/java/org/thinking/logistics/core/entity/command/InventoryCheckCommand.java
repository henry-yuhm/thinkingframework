package org.thinking.logistics.core.entity.command;

import org.thinking.logistics.core.entity.Batch;
import org.thinking.logistics.core.entity.bill.InventoryCheckDetail;
import org.thinking.logistics.core.entity.bill.InventoryCheckHeader;
import org.thinking.logistics.core.entity.container.Pallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class InventoryCheckCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InventoryCheckHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InventoryCheckDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batch sourceBatch;//源批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batch targetBatch;//目标批号

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet sourcePallet;//源托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet targetPallet;//目标托盘

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal inventoryQuantity = BigDecimal.ZERO;//库存数量

    @Column(nullable = false)
    private int inventoryPieces = 0;//库存件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal inventoryRemainder = BigDecimal.ZERO;//库存余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false)
    private int factPieces = 0;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factRemainder = BigDecimal.ZERO;//实际余数

    public InventoryCheckCommand() {
    }

    public InventoryCheckHeader getHeader() {
        return header;
    }

    public void setHeader(InventoryCheckHeader header) {
        this.header = header;
    }

    public InventoryCheckDetail getDetail() {
        return detail;
    }

    public void setDetail(InventoryCheckDetail detail) {
        this.detail = detail;
    }

    public Batch getSourceBatch() {
        return sourceBatch;
    }

    public void setSourceBatch(Batch sourceBatch) {
        this.sourceBatch = sourceBatch;
    }

    public Batch getTargetBatch() {
        return targetBatch;
    }

    public void setTargetBatch(Batch targetBatch) {
        this.targetBatch = targetBatch;
    }

    public Pallet getSourcePallet() {
        return sourcePallet;
    }

    public void setSourcePallet(Pallet sourcePallet) {
        this.sourcePallet = sourcePallet;
    }

    public Pallet getTargetPallet() {
        return targetPallet;
    }

    public void setTargetPallet(Pallet targetPallet) {
        this.targetPallet = targetPallet;
    }

    public BigDecimal getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(BigDecimal inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public int getInventoryPieces() {
        return inventoryPieces;
    }

    public void setInventoryPieces(int inventoryPieces) {
        this.inventoryPieces = inventoryPieces;
    }

    public BigDecimal getInventoryRemainder() {
        return inventoryRemainder;
    }

    public void setInventoryRemainder(BigDecimal inventoryRemainder) {
        this.inventoryRemainder = inventoryRemainder;
    }

    public BigDecimal getFactQuantity() {
        return factQuantity;
    }

    public void setFactQuantity(BigDecimal factQuantity) {
        this.factQuantity = factQuantity;
    }

    public int getFactPieces() {
        return factPieces;
    }

    public void setFactPieces(int factPieces) {
        this.factPieces = factPieces;
    }

    public BigDecimal getFactRemainder() {
        return factRemainder;
    }

    public void setFactRemainder(BigDecimal factRemainder) {
        this.factRemainder = factRemainder;
    }
}
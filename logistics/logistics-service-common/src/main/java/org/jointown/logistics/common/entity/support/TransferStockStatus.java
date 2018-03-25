package org.jointown.logistics.common.entity.support;

import javax.persistence.Embeddable;

@Embeddable
public class TransferStockStatus {
    private StockStatus sourceStockStatus;

    private StockStatus targetStockStatus;

    public StockStatus getSourceStockStatus() {
        return sourceStockStatus;
    }

    public void setSourceStockStatus(StockStatus sourceStockStatus) {
        this.sourceStockStatus = sourceStockStatus;
    }

    public StockStatus getTargetStockStatus() {
        return targetStockStatus;
    }

    public void setTargetStockStatus(StockStatus targetStockStatus) {
        this.targetStockStatus = targetStockStatus;
    }
}
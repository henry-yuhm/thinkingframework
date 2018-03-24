package org.jointown.logistics.common.entity.support;

import org.jointown.logistics.common.entity.Stock;

import javax.persistence.Embeddable;

@Embeddable
public class TransferStockStatus {
    private Stock.StockStatus sourceStockStatus;

    private Stock.StockStatus targetStockStatus;

    public Stock.StockStatus getSourceStockStatus() {
        return sourceStockStatus;
    }

    public void setSourceStockStatus(Stock.StockStatus sourceStockStatus) {
        this.sourceStockStatus = sourceStockStatus;
    }

    public Stock.StockStatus getTargetStockStatus() {
        return targetStockStatus;
    }

    public void setTargetStockStatus(Stock.StockStatus targetStockStatus) {
        this.targetStockStatus = targetStockStatus;
    }
}
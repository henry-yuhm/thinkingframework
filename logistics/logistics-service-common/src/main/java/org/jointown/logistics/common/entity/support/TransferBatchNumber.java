package org.jointown.logistics.common.entity.support;

import org.jointown.logistics.common.entity.BatchNumber;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Embeddable
public class TransferBatchNumber {
    @OneToOne(fetch = FetchType.LAZY)
    private BatchNumber sourceBatchNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private BatchNumber targetBatchNumber;

    public BatchNumber getSourceBatchNumber() {
        return sourceBatchNumber;
    }

    public void setSourceBatchNumber(BatchNumber sourceBatchNumber) {
        this.sourceBatchNumber = sourceBatchNumber;
    }

    public BatchNumber getTargetBatchNumber() {
        return targetBatchNumber;
    }

    public void setTargetBatchNumber(BatchNumber targetBatchNumber) {
        this.targetBatchNumber = targetBatchNumber;
    }
}
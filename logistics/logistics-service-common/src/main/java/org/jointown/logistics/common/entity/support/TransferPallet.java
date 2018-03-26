package org.jointown.logistics.common.entity.support;

import org.jointown.logistics.common.entity.container.Pallet;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Embeddable
public class TransferPallet {
    @OneToOne(fetch = FetchType.LAZY)
    private Pallet sourcePallet;

    @OneToOne(fetch = FetchType.LAZY)
    private Pallet targetPallet;

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
}
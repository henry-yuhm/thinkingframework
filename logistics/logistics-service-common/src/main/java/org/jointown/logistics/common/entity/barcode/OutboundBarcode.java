package org.jointown.logistics.common.entity.barcode;

import org.jointown.logistics.common.entity.SorterSlide;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class OutboundBarcode extends OperationBarcode {
    @OneToOne(fetch = FetchType.LAZY)
    private SorterSlide sorterSlide;//分拣滑道

    private boolean available;//是否可用

    private boolean collected;//是否集货

    public SorterSlide getSorterSlide() {
        return sorterSlide;
    }

    public void setSorterSlide(SorterSlide sorterSlide) {
        this.sorterSlide = sorterSlide;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}
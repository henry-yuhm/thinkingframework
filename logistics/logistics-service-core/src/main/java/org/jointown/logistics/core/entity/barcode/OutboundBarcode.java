package org.jointown.logistics.core.entity.barcode;

import org.jointown.logistics.core.entity.SorterSlide;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class OutboundBarcode extends TaskBarcode {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private SorterSlide slide;//分拣滑道

    @Column(nullable = false)
    private boolean available;//是否可用

    @Column(nullable = false)
    private boolean collected;//是否集货

    public SorterSlide getSlide() {
        return slide;
    }

    public void setSlide(SorterSlide slide) {
        this.slide = slide;
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
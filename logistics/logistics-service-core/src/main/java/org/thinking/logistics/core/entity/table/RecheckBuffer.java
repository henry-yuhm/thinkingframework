package org.thinking.logistics.core.entity.table;

import org.thinking.logistics.core.domain.support.RecheckBufferType;
import org.thinking.logistics.core.entity.Warehouse;

import javax.persistence.*;

@Entity
public class RecheckBuffer {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RecheckTable table;//复核台

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RecheckSlide slide;//复核滑道

    @Column(nullable = false)
    private RecheckBufferType type = RecheckBufferType.NORMAL;//类型

    @Column(nullable = false)
    private boolean available = true;//是否可用

    public RecheckBuffer() {
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public RecheckTable getTable() {
        return table;
    }

    public void setTable(RecheckTable table) {
        this.table = table;
    }

    public RecheckSlide getSlide() {
        return slide;
    }

    public void setSlide(RecheckSlide slide) {
        this.slide = slide;
    }

    public RecheckBufferType getType() {
        return type;
    }

    public void setType(RecheckBufferType type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
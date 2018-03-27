package org.jointown.logistics.core.entity.table;

import org.jointown.logistics.core.entity.Warehouse;
import org.jointown.logistics.core.entity.support.RecheckBufferType;

import javax.persistence.*;

@Entity
public class RecheckBuffer {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private RecheckTable table;//复核台

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private RecheckSlide slide;//复核滑道

    @Column(nullable = false)
    private RecheckBufferType type;//类型

    @Column(nullable = false)
    private boolean available;//是否可用

    public RecheckBuffer() {
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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
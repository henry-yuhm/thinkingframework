package org.jointown.logistics.core.entity.task;

import org.jointown.logistics.core.entity.Warehouse;

import javax.persistence.*;
import java.sql.Time;

@MappedSuperclass
public abstract class Task {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    private String picker;//拣货员

    private Time pickingStartTime;//拣货开始时间

    private Time pickingCompleteTime;//拣货完成时间

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

    public String getPicker() {
        return picker;
    }

    public void setPicker(String picker) {
        this.picker = picker;
    }

    public Time getPickingStartTime() {
        return pickingStartTime;
    }

    public void setPickingStartTime(Time pickingStartTime) {
        this.pickingStartTime = pickingStartTime;
    }

    public Time getPickingCompleteTime() {
        return pickingCompleteTime;
    }

    public void setPickingCompleteTime(Time pickingCompleteTime) {
        this.pickingCompleteTime = pickingCompleteTime;
    }
}
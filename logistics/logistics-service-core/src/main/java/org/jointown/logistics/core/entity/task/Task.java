package org.jointown.logistics.core.entity.task;

import org.jointown.logistics.core.entity.Warehouse;

import javax.persistence.*;
import java.sql.Date;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Task {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    private String picker;//拣货员

    private Date pickingStartTime;//拣货开始时间

    private Date pickingCompleteTime;//拣货完成时间

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

    public String getPicker() {
        return picker;
    }

    public void setPicker(String picker) {
        this.picker = picker;
    }

    public Date getPickingStartTime() {
        return pickingStartTime;
    }

    public void setPickingStartTime(Date pickingStartTime) {
        this.pickingStartTime = pickingStartTime;
    }

    public Date getPickingCompleteTime() {
        return pickingCompleteTime;
    }

    public void setPickingCompleteTime(Date pickingCompleteTime) {
        this.pickingCompleteTime = pickingCompleteTime;
    }
}
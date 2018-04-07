package org.thinking.logistics.core.entity.table;

import org.thinking.logistics.core.entity.Warehouse;
import org.thinking.logistics.core.entity.support.BillCategory;
import org.thinking.logistics.core.entity.support.RecheckTableType;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class RecheckTable {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @Column(nullable = false)
    private RecheckTableType type = RecheckTableType.NORMAL;//类型

    @Column(nullable = false)
    private BillCategory category;//类别

    @Column(nullable = false)
    private boolean locking = false;//是否锁定

    @Column(nullable = false)
    private boolean automatic;//是否自动化

    @Column(nullable = false)
    private int workload = 0;//工作量

    @Column(nullable = false)
    private int goodsQuantity = 0;//品规数

    private Date modificationTime;//修改时间

    public RecheckTable() {
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

    public RecheckTableType getType() {
        return type;
    }

    public void setType(RecheckTableType type) {
        this.type = type;
    }

    public BillCategory getCategory() {
        return category;
    }

    public void setCategory(BillCategory category) {
        this.category = category;
    }

    public boolean isLocking() {
        return locking;
    }

    public void setLocking(boolean locking) {
        this.locking = locking;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public int getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(int goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }
}
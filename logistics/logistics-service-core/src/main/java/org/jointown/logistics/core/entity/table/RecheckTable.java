package org.jointown.logistics.core.entity.table;

import org.jointown.logistics.core.entity.Warehouse;
import org.jointown.logistics.core.entity.support.RecheckTableCategory;
import org.jointown.logistics.core.entity.support.RecheckTableType;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Time;

@Entity
public class RecheckTable {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private RecheckTableType type;//类型

    @Column(nullable = false)
    private RecheckTableCategory category;//类别

    @Column(nullable = false)
    private boolean locking;//是否锁定

    @Column(nullable = false)
    private boolean automatic;//是否自动化

    @Column(nullable = false)
    private BigInteger workload;//工作量

    @Column(nullable = false)
    private BigInteger goodsNumber;//品规数

    private Time modificationTime;//修改时间

    public RecheckTable() {
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

    public RecheckTableType getType() {
        return type;
    }

    public void setType(RecheckTableType type) {
        this.type = type;
    }

    public RecheckTableCategory getCategory() {
        return category;
    }

    public void setCategory(RecheckTableCategory category) {
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

    public BigInteger getWorkload() {
        return workload;
    }

    public void setWorkload(BigInteger workload) {
        this.workload = workload;
    }

    public BigInteger getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(BigInteger goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Time getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Time modificationTime) {
        this.modificationTime = modificationTime;
    }
}
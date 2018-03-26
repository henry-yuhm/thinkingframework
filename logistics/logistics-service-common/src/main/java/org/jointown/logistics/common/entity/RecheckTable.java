package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.bill.TaskBill;
import org.jointown.logistics.common.entity.support.BufferType;
import org.jointown.logistics.common.entity.support.TableCategory;
import org.jointown.logistics.common.entity.support.TableType;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Time;

@Entity
public class RecheckTable {
    @Id
    @TableGenerator(name = "RecheckTableId", table = "RecheckTableId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RecheckTableId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_rct_wh"))
    private Warehouse warehouse;//仓库

    private String tableNo;//复核台编号

    private TableType tableType;//复核台类型

    private TableCategory tableCategory;//复核台类别

    private boolean available;//是否可用

    private boolean locking;//是否锁定

    private String bufferNo;//暂存位编号

    private BufferType bufferType;//暂存位类型

    private boolean automatic;//是否自动化

    private String slideNo;//滑道编号

    @Column(nullable = false)
    private BigInteger workload = BigInteger.ZERO;//工作量

    @Column(nullable = false)
    private BigInteger goodsNumber = BigInteger.ZERO;//品规数

    private TaskBill taskBill;//任务单

    private String splitBillNo;//拆分单编号

    private Time modificationTime;//修改时间

    public RecheckTable() {
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public TableType getTableType() {
        return tableType;
    }

    public void setTableType(TableType tableType) {
        this.tableType = tableType;
    }

    public TableCategory getTableCategory() {
        return tableCategory;
    }

    public void setTableCategory(TableCategory tableCategory) {
        this.tableCategory = tableCategory;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isLocking() {
        return locking;
    }

    public void setLocking(boolean locking) {
        this.locking = locking;
    }

    public String getBufferNo() {
        return bufferNo;
    }

    public void setBufferNo(String bufferNo) {
        this.bufferNo = bufferNo;
    }

    public BufferType getBufferType() {
        return bufferType;
    }

    public void setBufferType(BufferType bufferType) {
        this.bufferType = bufferType;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public String getSlideNo() {
        return slideNo;
    }

    public void setSlideNo(String slideNo) {
        this.slideNo = slideNo;
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

    public TaskBill getTaskBill() {
        return taskBill;
    }

    public void setTaskBill(TaskBill taskBill) {
        this.taskBill = taskBill;
    }

    public String getSplitBillNo() {
        return splitBillNo;
    }

    public void setSplitBillNo(String splitBillNo) {
        this.splitBillNo = splitBillNo;
    }

    public Time getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Time modificationTime) {
        this.modificationTime = modificationTime;
    }
}
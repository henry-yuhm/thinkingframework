package org.thinking.logistics.core.entity.task;

import org.thinking.logistics.core.entity.container.Totebox;
import org.thinking.logistics.core.entity.table.RecheckBuffer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
public class RemainderTask extends Task {
    private String splittingBill;//拆分单

    @ManyToOne(fetch = FetchType.LAZY)
    private Totebox totebox;//周转箱

    @ManyToOne(fetch = FetchType.LAZY)
    private RecheckBuffer buffer;//复核暂存位

    private int batchNumber;//任务批次号

    private String bufferNumber;//暂存位编号

    private String rechecker;//复核员

    private Date recheckStartTime;//复核开始时间

    private Date recheckCompleteTime;//复核完成时间

    public RemainderTask() {
    }

    public String getSplittingBill() {
        return splittingBill;
    }

    public void setSplittingBill(String splittingBill) {
        this.splittingBill = splittingBill;
    }

    public Totebox getTotebox() {
        return totebox;
    }

    public void setTotebox(Totebox totebox) {
        this.totebox = totebox;
    }

    public RecheckBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(RecheckBuffer buffer) {
        this.buffer = buffer;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getBufferNumber() {
        return bufferNumber;
    }

    public void setBufferNumber(String bufferNumber) {
        this.bufferNumber = bufferNumber;
    }

    public String getRechecker() {
        return rechecker;
    }

    public void setRechecker(String rechecker) {
        this.rechecker = rechecker;
    }

    public Date getRecheckStartTime() {
        return recheckStartTime;
    }

    public void setRecheckStartTime(Date recheckStartTime) {
        this.recheckStartTime = recheckStartTime;
    }

    public Date getRecheckCompleteTime() {
        return recheckCompleteTime;
    }

    public void setRecheckCompleteTime(Date recheckCompleteTime) {
        this.recheckCompleteTime = recheckCompleteTime;
    }
}
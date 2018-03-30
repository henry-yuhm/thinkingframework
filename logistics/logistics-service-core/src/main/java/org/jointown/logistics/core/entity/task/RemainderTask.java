package org.jointown.logistics.core.entity.task;

import org.jointown.logistics.core.entity.container.Totebox;
import org.jointown.logistics.core.entity.table.RecheckBuffer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigInteger;
import java.sql.Time;

@Entity
public class RemainderTask extends Task {
    private String splittingBill;//拆分单

    @OneToOne(fetch = FetchType.LAZY)
    private Totebox totebox;//周转箱

    @OneToOne(fetch = FetchType.LAZY)
    private RecheckBuffer buffer;//复核暂存位

    private BigInteger batchno;//任务批次号

    private String bufferNo;//暂存位编号

    private String rechecker;//复核员

    private Time recheckStartTime;//复核开始时间

    private Time recheckCompleteTime;//复核完成时间

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

    public BigInteger getBatchno() {
        return batchno;
    }

    public void setBatchno(BigInteger batchno) {
        this.batchno = batchno;
    }

    public String getBufferNo() {
        return bufferNo;
    }

    public void setBufferNo(String bufferNo) {
        this.bufferNo = bufferNo;
    }

    public String getRechecker() {
        return rechecker;
    }

    public void setRechecker(String rechecker) {
        this.rechecker = rechecker;
    }

    public Time getRecheckStartTime() {
        return recheckStartTime;
    }

    public void setRecheckStartTime(Time recheckStartTime) {
        this.recheckStartTime = recheckStartTime;
    }

    public Time getRecheckCompleteTime() {
        return recheckCompleteTime;
    }

    public void setRecheckCompleteTime(Time recheckCompleteTime) {
        this.recheckCompleteTime = recheckCompleteTime;
    }
}
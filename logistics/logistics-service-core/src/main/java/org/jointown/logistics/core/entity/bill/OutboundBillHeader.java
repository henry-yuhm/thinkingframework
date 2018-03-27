package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.support.OutboundBillStage;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Time;

@MappedSuperclass
public abstract class OutboundBillHeader extends BillHeader {
    @Column(nullable = false)
    private OutboundBillStage stage;//单据阶段

    @Column(nullable = false)
    private boolean wholeBillReversed;//是否整单冲红

    @Column(nullable = false)
    private boolean reverseAudit;//是否冲红审核

    @Column(nullable = false)
    private boolean uploaded;//是否上传

    @Column(nullable = false)
    private boolean collectionComplete;//是否集货完成

    private Time taskCompleteTime;//作业完成时间

    private String rechecker;//复核员

    private Time recheckStartTime;//复核开始时间

    private Time recheckCompleteTime;//复核结束时间

    @Column(nullable = false)
    private boolean stagingAreaCleaned;//是否清空月台

    private String businessman;//业务员

    private String buyer;//采购员

    public OutboundBillStage getStage() {
        return stage;
    }

    public void setStage(OutboundBillStage stage) {
        this.stage = stage;
    }

    public boolean isWholeBillReversed() {
        return wholeBillReversed;
    }

    public void setWholeBillReversed(boolean wholeBillReversed) {
        this.wholeBillReversed = wholeBillReversed;
    }

    public boolean isReverseAudit() {
        return reverseAudit;
    }

    public void setReverseAudit(boolean reverseAudit) {
        this.reverseAudit = reverseAudit;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public boolean isCollectionComplete() {
        return collectionComplete;
    }

    public void setCollectionComplete(boolean collectionComplete) {
        this.collectionComplete = collectionComplete;
    }

    public Time getTaskCompleteTime() {
        return taskCompleteTime;
    }

    public void setTaskCompleteTime(Time taskCompleteTime) {
        this.taskCompleteTime = taskCompleteTime;
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

    public boolean isStagingAreaCleaned() {
        return stagingAreaCleaned;
    }

    public void setStagingAreaCleaned(boolean stagingAreaCleaned) {
        this.stagingAreaCleaned = stagingAreaCleaned;
    }

    public String getBusinessman() {
        return businessman;
    }

    public void setBusinessman(String businessman) {
        this.businessman = businessman;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}
package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.Address;
import org.jointown.logistics.core.entity.Stagingarea;
import org.jointown.logistics.core.entity.support.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Time;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class OutboundHeader extends Header {
    @Column(nullable = false)
    private OutboundStage stage;//出库阶段

    @Column(nullable = false)
    private OutboundPriority priority;//出库优先级

    @Column(nullable = false)
    private TakegoodsMode takegoodsMode;//提货方式

    @Column(nullable = false)
    private TakegoodsMode takegoodsModeSwitch;//提货方式转换

    @Column(nullable = false)
    private SaleType saleType;//销售类型

    private String deliveryType;//配送类型

    @Column(nullable = false)
    private Time invoiceTime;//开票时间

    private String settlementType;//结算类型

    private Time settlementTime;//结算时间

    private TaxType taxType;//税票类型

    private String taxName;//税票名称

    @Column(nullable = false)
    private boolean printContract;//是否打印合同

    private String wave;//波次

    @Column(nullable = false)
    private DispatchType dispatchType;//调度类型

    private String dispatcher;//调度员

    private Time dispatchTime;//调度时间

    private Time releaseTime;//下发时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;//地址

    @ManyToOne(fetch = FetchType.LAZY)
    private Stagingarea sourceStagingarea;//起始月台

    @ManyToOne(fetch = FetchType.LAZY)
    private Stagingarea targetStagingarea;//终止月台

    @Column(nullable = false)
    private boolean inversed;//是否整单冲红

    @Column(nullable = false)
    private boolean auditing;//是否冲红审核

    @Column(nullable = false)
    private boolean uploaded;//是否上传

    @Column(nullable = false)
    private boolean collectionComplete;//是否集货完成

    private Time taskCompleteTime;//作业完成时间

    private String rechecker;//复核员

    private Time recheckStartTime;//复核开始时间

    private Time recheckCompleteTime;//复核结束时间

    @Column(nullable = false)
    private String recheckbillPrintSign;//复核单打印标识

    private String recheckbillPrintClerk;//复核单打印员

    private Time recheckbillPrintTime;//复核单打印时间

    @Column(nullable = false)
    private String reportbillPrintSign;//报告单打印标识

    private String reportbillPrintClerk;//报告单打印员

    private Time reportbillPrintTime;//报告单打印时间

    @Column(nullable = false)
    private BigInteger printTimes;//打印次数

    @Column(nullable = false)
    private boolean stagingAreaCleaned;//是否清空月台

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<OutboundDetail> details = new LinkedHashSet<>();//单据明细

    public OutboundHeader() {
    }

    public OutboundStage getStage() {
        return stage;
    }

    public void setStage(OutboundStage stage) {
        this.stage = stage;
    }

    public OutboundPriority getPriority() {
        return priority;
    }

    public void setPriority(OutboundPriority priority) {
        this.priority = priority;
    }

    public TakegoodsMode getTakegoodsMode() {
        return takegoodsMode;
    }

    public void setTakegoodsMode(TakegoodsMode takegoodsMode) {
        this.takegoodsMode = takegoodsMode;
    }

    public TakegoodsMode getTakegoodsModeSwitch() {
        return takegoodsModeSwitch;
    }

    public void setTakegoodsModeSwitch(TakegoodsMode takegoodsModeSwitch) {
        this.takegoodsModeSwitch = takegoodsModeSwitch;
    }

    public SaleType getSaleType() {
        return saleType;
    }

    public void setSaleType(SaleType saleType) {
        this.saleType = saleType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Time getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(Time invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public Time getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(Time settlementTime) {
        this.settlementTime = settlementTime;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public boolean isPrintContract() {
        return printContract;
    }

    public void setPrintContract(boolean printContract) {
        this.printContract = printContract;
    }

    public String getWave() {
        return wave;
    }

    public void setWave(String wave) {
        this.wave = wave;
    }

    public DispatchType getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(DispatchType dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    public Time getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Time dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public Time getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Time releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Stagingarea getSourceStagingarea() {
        return sourceStagingarea;
    }

    public void setSourceStagingarea(Stagingarea sourceStagingarea) {
        this.sourceStagingarea = sourceStagingarea;
    }

    public Stagingarea getTargetStagingarea() {
        return targetStagingarea;
    }

    public void setTargetStagingarea(Stagingarea targetStagingarea) {
        this.targetStagingarea = targetStagingarea;
    }

    public boolean isInversed() {
        return inversed;
    }

    public void setInversed(boolean inversed) {
        this.inversed = inversed;
    }

    public boolean isAuditing() {
        return auditing;
    }

    public void setAuditing(boolean auditing) {
        this.auditing = auditing;
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

    public String getRecheckbillPrintSign() {
        return recheckbillPrintSign;
    }

    public void setRecheckbillPrintSign(String recheckbillPrintSign) {
        this.recheckbillPrintSign = recheckbillPrintSign;
    }

    public String getRecheckbillPrintClerk() {
        return recheckbillPrintClerk;
    }

    public void setRecheckbillPrintClerk(String recheckbillPrintClerk) {
        this.recheckbillPrintClerk = recheckbillPrintClerk;
    }

    public Time getRecheckbillPrintTime() {
        return recheckbillPrintTime;
    }

    public void setRecheckbillPrintTime(Time recheckbillPrintTime) {
        this.recheckbillPrintTime = recheckbillPrintTime;
    }

    public String getReportbillPrintSign() {
        return reportbillPrintSign;
    }

    public void setReportbillPrintSign(String reportbillPrintSign) {
        this.reportbillPrintSign = reportbillPrintSign;
    }

    public String getReportbillPrintClerk() {
        return reportbillPrintClerk;
    }

    public void setReportbillPrintClerk(String reportbillPrintClerk) {
        this.reportbillPrintClerk = reportbillPrintClerk;
    }

    public Time getReportbillPrintTime() {
        return reportbillPrintTime;
    }

    public void setReportbillPrintTime(Time reportbillPrintTime) {
        this.reportbillPrintTime = reportbillPrintTime;
    }

    public BigInteger getPrintTimes() {
        return printTimes;
    }

    public void setPrintTimes(BigInteger printTimes) {
        this.printTimes = printTimes;
    }

    public boolean isStagingAreaCleaned() {
        return stagingAreaCleaned;
    }

    public void setStagingAreaCleaned(boolean stagingAreaCleaned) {
        this.stagingAreaCleaned = stagingAreaCleaned;
    }

    @Override
    public Set<OutboundDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<OutboundDetail> details) {
        this.details = details;
    }
}
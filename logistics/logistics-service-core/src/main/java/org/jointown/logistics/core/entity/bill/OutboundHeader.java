package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.Address;
import org.jointown.logistics.core.entity.Stagingarea;
import org.jointown.logistics.core.entity.support.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class OutboundHeader extends Header {
    @Column(nullable = false)
    private OutboundStage stage = OutboundStage.CREATED;//出库阶段

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
    private Date invoiceTime;//开票时间

    private String settlementType = "无";//结算类型

    private Date settlementTime;//结算时间

    private TaxType taxType;//税票类型

    private String taxName;//税票名称

    @Column(nullable = false)
    private boolean printContract = false;//是否打印合同

    private String wave;//波次

    @Column(nullable = false)
    private DispatchType dispatchType = DispatchType.AUTOMATIC;//调度类型

    private String dispatcher;//调度员

    private Date dispatchTime;//调度时间

    private Date releaseTime;//下发时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;//地址

    @ManyToOne(fetch = FetchType.LAZY)
    private Stagingarea sourceStagingarea;//起始月台

    @ManyToOne(fetch = FetchType.LAZY)
    private Stagingarea targetStagingarea;//终止月台

    @Column(nullable = false)
    private boolean inversed = false;//是否整单冲红

    @Column(nullable = false)
    private boolean auditing = false;//是否冲红审核

    @Column(nullable = false)
    private boolean uploaded = false;//是否上传

    @Column(nullable = false)
    private boolean collectionComplete = false;//是否集货完成

    private Date taskCompleteTime;//作业完成时间

    private String rechecker;//复核员

    private Date recheckStartTime;//复核开始时间

    private Date recheckCompleteTime;//复核结束时间

    @Column(nullable = false)
    private String recheckbillPrintSign = "N";//复核单打印标识

    private String recheckbillPrintClerk;//复核单打印员

    private Date recheckbillPrintTime;//复核单打印时间

    @Column(nullable = false)
    private String reportbillPrintSign = "N";//报告单打印标识

    private String reportbillPrintClerk;//报告单打印员

    private Date reportbillPrintTime;//报告单打印时间

    @Column(nullable = false)
    private int printTimes = 0;//打印次数

    @Column(nullable = false)
    private boolean stagingareaCleaned = false;//是否清空月台

    @Column(nullable = false)
    private int goodsQuantity = 0;//品规数

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

    public Date getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(Date invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public Date getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(Date settlementTime) {
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

    public Date getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
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

    public Date getTaskCompleteTime() {
        return taskCompleteTime;
    }

    public void setTaskCompleteTime(Date taskCompleteTime) {
        this.taskCompleteTime = taskCompleteTime;
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

    public Date getRecheckbillPrintTime() {
        return recheckbillPrintTime;
    }

    public void setRecheckbillPrintTime(Date recheckbillPrintTime) {
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

    public Date getReportbillPrintTime() {
        return reportbillPrintTime;
    }

    public void setReportbillPrintTime(Date reportbillPrintTime) {
        this.reportbillPrintTime = reportbillPrintTime;
    }

    public int getPrintTimes() {
        return printTimes;
    }

    public void setPrintTimes(int printTimes) {
        this.printTimes = printTimes;
    }

    public boolean isStagingareaCleaned() {
        return stagingareaCleaned;
    }

    public void setStagingareaCleaned(boolean stagingareaCleaned) {
        this.stagingareaCleaned = stagingareaCleaned;
    }

    @Override
    public Set<OutboundDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<OutboundDetail> details) {
        this.details = details;
    }
}
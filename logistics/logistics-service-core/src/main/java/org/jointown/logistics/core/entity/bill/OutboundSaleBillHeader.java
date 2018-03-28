package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.Address;
import org.jointown.logistics.core.entity.StagingArea;
import org.jointown.logistics.core.entity.support.DispatchType;
import org.jointown.logistics.core.entity.support.SaleType;
import org.jointown.logistics.core.entity.support.TakegoodsMode;
import org.jointown.logistics.core.entity.support.TaxType;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Entity
public class OutboundSaleBillHeader extends OutboundBillHeader {
    @Column(nullable = false)
    private TakegoodsMode takegoodsMode;//提货方式

    @Column(nullable = false)
    private TakegoodsMode takegoodsModeSwitch;//提货方式转换

    @Column(nullable = false)
    private SaleType saleType;//销售类型

    private String wave;//波次

    @Column(nullable = false)
    private DispatchType dispatchType;//调度类型

    private String dispatcher;//调度员

    private Time dispatchTime;//调度时间

    private Time releaseTime;//下发时间

    private String deliveryType;//配送类型

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;//地址

    @OneToOne(fetch = FetchType.LAZY)
    private StagingArea sourceStagingArea;//起始月台

    @OneToOne(fetch = FetchType.LAZY)
    private StagingArea targetStagingArea;//终止月台

    private boolean stockout;//是否缺货

    @Column(nullable = false)
    private Time invoiceTime;//开票时间

    private String settlementType;//结算类型

    private Time settlementTime;//结算时间

    private TaxType taxType;//税票类型

    private String taxName;//税票名称

    @Column(nullable = false)
    private boolean printContract;//是否打印合同

    @Column(nullable = false)
    private String recheckBillPrintSign;//复核单打印标识

    private String recheckBillPrintClerk;//复核单打印员

    private Time recheckBillPrintTime;//复核单打印时间

    @Column(nullable = false)
    private String reportBillPrintSign;//报告单打印标识

    private String reportBillPrintClerk;//报告单打印员

    private Time reportBillPrintTime;//报告单打印时间

    @OneToMany
    private Set<OutboundSaleBillDetail> details;//单据明细

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

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public StagingArea getSourceStagingArea() {
        return sourceStagingArea;
    }

    public void setSourceStagingArea(StagingArea sourceStagingArea) {
        this.sourceStagingArea = sourceStagingArea;
    }

    public StagingArea getTargetStagingArea() {
        return targetStagingArea;
    }

    public void setTargetStagingArea(StagingArea targetStagingArea) {
        this.targetStagingArea = targetStagingArea;
    }

    public boolean isStockout() {
        return stockout;
    }

    public void setStockout(boolean stockout) {
        this.stockout = stockout;
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

    public String getRecheckBillPrintSign() {
        return recheckBillPrintSign;
    }

    public void setRecheckBillPrintSign(String recheckBillPrintSign) {
        this.recheckBillPrintSign = recheckBillPrintSign;
    }

    public String getRecheckBillPrintClerk() {
        return recheckBillPrintClerk;
    }

    public void setRecheckBillPrintClerk(String recheckBillPrintClerk) {
        this.recheckBillPrintClerk = recheckBillPrintClerk;
    }

    public Time getRecheckBillPrintTime() {
        return recheckBillPrintTime;
    }

    public void setRecheckBillPrintTime(Time recheckBillPrintTime) {
        this.recheckBillPrintTime = recheckBillPrintTime;
    }

    public String getReportBillPrintSign() {
        return reportBillPrintSign;
    }

    public void setReportBillPrintSign(String reportBillPrintSign) {
        this.reportBillPrintSign = reportBillPrintSign;
    }

    public String getReportBillPrintClerk() {
        return reportBillPrintClerk;
    }

    public void setReportBillPrintClerk(String reportBillPrintClerk) {
        this.reportBillPrintClerk = reportBillPrintClerk;
    }

    public Time getReportBillPrintTime() {
        return reportBillPrintTime;
    }

    public void setReportBillPrintTime(Time reportBillPrintTime) {
        this.reportBillPrintTime = reportBillPrintTime;
    }

    @Override
    public Set<OutboundSaleBillDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<OutboundSaleBillDetail> details) {
        this.details = details;
    }
}
package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.Address;
import org.jointown.logistics.core.entity.Stagingarea;
import org.jointown.logistics.core.entity.support.DispatchType;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Entity
public class SaleOutboundHeader extends OutboundHeader {
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private SaleOrderHeader orderHeader;//订单抬头

    private String wave;//波次

    @Column(nullable = false)
    private DispatchType dispatchType;//调度类型

    private String dispatcher;//调度员

    private Time dispatchTime;//调度时间

    private Time releaseTime;//下发时间

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;//地址

    @OneToOne(fetch = FetchType.LAZY)
    private Stagingarea sourceStagingarea;//起始月台

    @OneToOne(fetch = FetchType.LAZY)
    private Stagingarea targetStagingarea;//终止月台

    @Column(nullable = false)
    private String recheckbillPrintSign;//复核单打印标识

    private String recheckbillPrintClerk;//复核单打印员

    private Time recheckbillPrintTime;//复核单打印时间

    @Column(nullable = false)
    private String reportbillPrintSign;//报告单打印标识

    private String reportbillPrintClerk;//报告单打印员

    private Time reportbillPrintTime;//报告单打印时间

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "header_id")}, inverseJoinColumns = {@JoinColumn(name = "detail_id")})
    private Set<SaleOutboundDetail> details;//单据明细

    public SaleOrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(SaleOrderHeader orderHeader) {
        this.orderHeader = orderHeader;
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

    @Override
    public Set<SaleOutboundDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<SaleOutboundDetail> details) {
        this.details = details;
    }
}
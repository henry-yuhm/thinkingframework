package org.thinking.logistics.core.entity.bill;

import org.thinking.logistics.core.domain.support.ArrivalMode;
import org.thinking.logistics.core.domain.support.ArrivalVoucher;
import org.thinking.logistics.core.domain.support.InboundStage;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class InboundHeader extends Header {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOrderHeader order;//订单

    @Column(nullable = false)
    private InboundStage stage = InboundStage.CREATED;//入库阶段

    @Column(nullable = false)
    private String receivingClerk;//收货员

    private String auditor;//审核员

    private String inspector;//质检员

    @Column(nullable = false)
    private boolean passback = true;//是否回传

    @Column(nullable = false)
    private boolean chargeup = false;//是否记账

    @Column(nullable = false)
    private boolean executed = false;//是否执行

    @Column(nullable = false)
    private boolean complete = false;//是否完成

    private ArrivalMode arrivalMode;//到货方式

    @Column(nullable = false)
    private ArrivalVoucher arrivalVoucher = ArrivalVoucher.HOLD;//到货凭证

    private String arrivalNo;//到货单号

    private Date arrivalTime;//到货时间

    private String saleOrderNo;//销售订单号

    @Column(nullable = false)
    private boolean printed = false;//是否打印

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<InboundDetail> details = new LinkedHashSet<>();//单据明细

    public InboundHeader() {
    }

    public PurchaseOrderHeader getOrder() {
        return order;
    }

    public void setOrder(PurchaseOrderHeader order) {
        this.order = order;
    }

    public InboundStage getStage() {
        return stage;
    }

    public void setStage(InboundStage stage) {
        this.stage = stage;
    }

    public String getReceivingClerk() {
        return receivingClerk;
    }

    public void setReceivingClerk(String receivingClerk) {
        this.receivingClerk = receivingClerk;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public boolean isPassback() {
        return passback;
    }

    public void setPassback(boolean passback) {
        this.passback = passback;
    }

    public boolean isChargeup() {
        return chargeup;
    }

    public void setChargeup(boolean chargeup) {
        this.chargeup = chargeup;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public ArrivalMode getArrivalMode() {
        return arrivalMode;
    }

    public void setArrivalMode(ArrivalMode arrivalMode) {
        this.arrivalMode = arrivalMode;
    }

    public ArrivalVoucher getArrivalVoucher() {
        return arrivalVoucher;
    }

    public void setArrivalVoucher(ArrivalVoucher arrivalVoucher) {
        this.arrivalVoucher = arrivalVoucher;
    }

    public String getArrivalNo() {
        return arrivalNo;
    }

    public void setArrivalNo(String arrivalNo) {
        this.arrivalNo = arrivalNo;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getSaleOrderNo() {
        return saleOrderNo;
    }

    public void setSaleOrderNo(String saleOrderNo) {
        this.saleOrderNo = saleOrderNo;
    }

    public boolean isPrinted() {
        return printed;
    }

    public void setPrinted(boolean printed) {
        this.printed = printed;
    }

    @Override
    public Set<InboundDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<InboundDetail> details) {
        this.details = details;
    }
}
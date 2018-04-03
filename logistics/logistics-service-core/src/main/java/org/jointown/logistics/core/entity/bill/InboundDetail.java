package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.container.Pallet;
import org.jointown.logistics.core.entity.container.Totebox;
import org.jointown.logistics.core.entity.support.Assessment;
import org.jointown.logistics.core.entity.support.Conveyance;
import org.jointown.logistics.core.entity.support.ReceiptConclusion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
public class InboundDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY)
    private InboundDetail parent;//父明细

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private InboundHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOrderDetail order;//订单

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planQuantity;//计划数量

    @Column(nullable = false)
    private BigInteger planPieces;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planRemainder;//计划余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity;//实际数量

    @Column(nullable = false)
    private BigInteger factPieces;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factRemainder;//实际余数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Totebox totebox;//周转箱

    @Column(nullable = false)
    private Assessment assessment;//验收评定

    @Column(nullable = false)
    private ReceiptConclusion conclusion;//收货结论

    private String rejections;//拒收原因

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal price;//单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal amount;//金额

    @Column(nullable = false)
    private String storeNo;//库房编号

    private String splitter;//拆分人

    private BigInteger temperature;//温度

    private Conveyance conveyance;//运输工具

    private String scanningNo;//扫描单号

    public InboundDetail() {
    }

    public InboundDetail getParent() {
        return parent;
    }

    public void setParent(InboundDetail parent) {
        this.parent = parent;
    }

//    @Override
//    public InboundHeader getHeader() {
//        return header;
//    }
//
//    public void setHeader(InboundHeader header) {
//        this.header = header;
//    }

    public PurchaseOrderDetail getOrder() {
        return order;
    }

    public void setOrder(PurchaseOrderDetail order) {
        this.order = order;
    }

    public BigDecimal getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(BigDecimal planQuantity) {
        this.planQuantity = planQuantity;
    }

    public BigInteger getPlanPieces() {
        return planPieces;
    }

    public void setPlanPieces(BigInteger planPieces) {
        this.planPieces = planPieces;
    }

    public BigDecimal getPlanRemainder() {
        return planRemainder;
    }

    public void setPlanRemainder(BigDecimal planRemainder) {
        this.planRemainder = planRemainder;
    }

    public BigDecimal getFactQuantity() {
        return factQuantity;
    }

    public void setFactQuantity(BigDecimal factQuantity) {
        this.factQuantity = factQuantity;
    }

    public BigInteger getFactPieces() {
        return factPieces;
    }

    public void setFactPieces(BigInteger factPieces) {
        this.factPieces = factPieces;
    }

    public BigDecimal getFactRemainder() {
        return factRemainder;
    }

    public void setFactRemainder(BigDecimal factRemainder) {
        this.factRemainder = factRemainder;
    }

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    public Totebox getTotebox() {
        return totebox;
    }

    public void setTotebox(Totebox totebox) {
        this.totebox = totebox;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public ReceiptConclusion getConclusion() {
        return conclusion;
    }

    public void setConclusion(ReceiptConclusion conclusion) {
        this.conclusion = conclusion;
    }

    public String getRejections() {
        return rejections;
    }

    public void setRejections(String rejections) {
        this.rejections = rejections;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getSplitter() {
        return splitter;
    }

    public void setSplitter(String splitter) {
        this.splitter = splitter;
    }

    public BigInteger getTemperature() {
        return temperature;
    }

    public void setTemperature(BigInteger temperature) {
        this.temperature = temperature;
    }

    public Conveyance getConveyance() {
        return conveyance;
    }

    public void setConveyance(Conveyance conveyance) {
        this.conveyance = conveyance;
    }

    public String getScanningNo() {
        return scanningNo;
    }

    public void setScanningNo(String scanningNo) {
        this.scanningNo = scanningNo;
    }
}
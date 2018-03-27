package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.support.BatchRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class OutboundSaleBillDetail extends OutboundBillDetail {
    @OneToOne(fetch = FetchType.LAZY)
    private OutboundSaleBillDetail parent;//父单据明细

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private OutboundSaleBillHeader header;//单据抬头

    private BatchRequest batchRequest;//批号要求

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal lessnessQuantity;//库存不足数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal price;//单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal amount;//金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal taxes;//税票金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settlementPrice;//结算单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settlementAmount;//结算金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal flittingCostPrice;//调拨成本价

    public OutboundSaleBillDetail getParent() {
        return parent;
    }

    public void setParent(OutboundSaleBillDetail parent) {
        this.parent = parent;
    }

    public OutboundSaleBillHeader getHeader() {
        return header;
    }

    public void setHeader(OutboundSaleBillHeader header) {
        this.header = header;
    }

    public BatchRequest getBatchRequest() {
        return batchRequest;
    }

    public void setBatchRequest(BatchRequest batchRequest) {
        this.batchRequest = batchRequest;
    }

    public BigDecimal getLessnessQuantity() {
        return lessnessQuantity;
    }

    public void setLessnessQuantity(BigDecimal lessnessQuantity) {
        this.lessnessQuantity = lessnessQuantity;
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

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(BigDecimal settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public BigDecimal getFlittingCostPrice() {
        return flittingCostPrice;
    }

    public void setFlittingCostPrice(BigDecimal flittingCostPrice) {
        this.flittingCostPrice = flittingCostPrice;
    }
}
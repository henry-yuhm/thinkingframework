package org.thinking.logistics.core.entity.bill;

import org.thinking.logistics.core.entity.support.BatchRequest;
import org.thinking.logistics.core.entity.support.InventoryState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class OutboundDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY)
    private OutboundDetail parent;//父明细

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private OutboundHeader header;//抬头

    @Column(nullable = false)
    private InventoryState state = InventoryState.QUALIFICATION;//库存状态

    private BatchRequest request = BatchRequest.NONE;//批号要求

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planQuantity = BigDecimal.ZERO;//计划数量

    @Column(nullable = false)
    private int planPieces = 0;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planRemainder = BigDecimal.ZERO;//计划余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false)
    private int factPieces = 0;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factRemainder = BigDecimal.ZERO;//实际余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal wholeQuantity = BigDecimal.ZERO;//整件未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainderQuantity = BigDecimal.ZERO;//零货未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal lessnessQuantity = BigDecimal.ZERO;//库存不足数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal price = BigDecimal.ZERO;//单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal amount = BigDecimal.ZERO;//金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal taxes = BigDecimal.ZERO;//税票金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settlementPrice = BigDecimal.ZERO;//结算单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settlementAmount = BigDecimal.ZERO;//结算金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal flittingCostPrice = BigDecimal.ZERO;//调拨成本价

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "detail_id"), inverseJoinColumns = @JoinColumn(name = "supplement_id"))
    private Set<SupplementDetail> details = new LinkedHashSet<>();//增补明细

    public OutboundDetail() {
    }

    public OutboundDetail getParent() {
        return parent;
    }

    public void setParent(OutboundDetail parent) {
        this.parent = parent;
    }

//    @Override
//    public OutboundHeader getHeader() {
//        return header;
//    }
//
//    public void setHeader(OutboundHeader header) {
//        this.header = header;
//    }

    public InventoryState getState() {
        return state;
    }

    public void setState(InventoryState state) {
        this.state = state;
    }

    public BatchRequest getRequest() {
        return request;
    }

    public void setRequest(BatchRequest request) {
        this.request = request;
    }

    public BigDecimal getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(BigDecimal planQuantity) {
        this.planQuantity = planQuantity;
    }

    public int getPlanPieces() {
        return planPieces;
    }

    public void setPlanPieces(int planPieces) {
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

    public int getFactPieces() {
        return factPieces;
    }

    public void setFactPieces(int factPieces) {
        this.factPieces = factPieces;
    }

    public BigDecimal getFactRemainder() {
        return factRemainder;
    }

    public void setFactRemainder(BigDecimal factRemainder) {
        this.factRemainder = factRemainder;
    }

    public BigDecimal getWholeQuantity() {
        return wholeQuantity;
    }

    public void setWholeQuantity(BigDecimal wholeQuantity) {
        this.wholeQuantity = wholeQuantity;
    }

    public BigDecimal getRemainderQuantity() {
        return remainderQuantity;
    }

    public void setRemainderQuantity(BigDecimal remainderQuantity) {
        this.remainderQuantity = remainderQuantity;
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

    public Set<SupplementDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<SupplementDetail> details) {
        this.details = details;
    }
}
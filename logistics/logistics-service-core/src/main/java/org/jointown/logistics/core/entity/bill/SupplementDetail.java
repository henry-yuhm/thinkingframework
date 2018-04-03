package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.Location;
import org.jointown.logistics.core.entity.support.StockState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
public class SupplementDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    @Column(nullable = false)
    private StockState state;//库存状态

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

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal wholeQuantity;//整件未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainderQuantity;//零货未处理数量

    public SupplementDetail() {
    }

    public OutboundHeader getHeader() {
        return header;
    }

    public void setHeader(OutboundHeader header) {
        this.header = header;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public StockState getState() {
        return state;
    }

    public void setState(StockState state) {
        this.state = state;
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
}
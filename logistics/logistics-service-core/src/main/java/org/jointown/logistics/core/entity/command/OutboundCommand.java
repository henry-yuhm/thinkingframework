package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.Location;
import org.jointown.logistics.core.entity.bill.OutboundDetail;
import org.jointown.logistics.core.entity.bill.OutboundHeader;
import org.jointown.logistics.core.entity.support.AppendixSign;
import org.jointown.logistics.core.entity.support.StockState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class OutboundCommand extends Command {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;//货位

    @Column(nullable = false)
    private StockState state;//库存状态

    @Column(nullable = false)
    private boolean active;//是否激活

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creationQuantity;//创建数量

    @Column(nullable = false)
    private BigInteger creationPieces;//创建件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creationRemainder;//创建余数

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

    @Column(nullable = false)
    private AppendixSign appendixSign;//追加标识

    @Column(nullable = false)
    private String pickingOrder;//拣货顺序

    public abstract Set<ReplenishmentCommand> getCommands();

    public abstract void setCommands(Set<ReplenishmentCommand> commands);

    public OutboundHeader getHeader() {
        return header;
    }

    public void setHeader(OutboundHeader header) {
        this.header = header;
    }

    public OutboundDetail getDetail() {
        return detail;
    }

    public void setDetail(OutboundDetail detail) {
        this.detail = detail;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BigDecimal getCreationQuantity() {
        return creationQuantity;
    }

    public void setCreationQuantity(BigDecimal creationQuantity) {
        this.creationQuantity = creationQuantity;
    }

    public BigInteger getCreationPieces() {
        return creationPieces;
    }

    public void setCreationPieces(BigInteger creationPieces) {
        this.creationPieces = creationPieces;
    }

    public BigDecimal getCreationRemainder() {
        return creationRemainder;
    }

    public void setCreationRemainder(BigDecimal creationRemainder) {
        this.creationRemainder = creationRemainder;
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

    public AppendixSign getAppendixSign() {
        return appendixSign;
    }

    public void setAppendixSign(AppendixSign appendixSign) {
        this.appendixSign = appendixSign;
    }

    public String getPickingOrder() {
        return pickingOrder;
    }

    public void setPickingOrder(String pickingOrder) {
        this.pickingOrder = pickingOrder;
    }
}
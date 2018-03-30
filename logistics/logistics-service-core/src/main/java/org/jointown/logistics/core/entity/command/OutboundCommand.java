package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.Location;
import org.jointown.logistics.core.entity.bill.SaleOutboundDetail;
import org.jointown.logistics.core.entity.bill.SaleOutboundHeader;
import org.jointown.logistics.core.entity.support.AppendixSign;
import org.jointown.logistics.core.entity.support.StockStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

@MappedSuperclass
public abstract class OutboundCommand extends Command {
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private SaleOutboundHeader header;//单据抬头

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private SaleOutboundDetail detail;//单据明细

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;//货位

    @Column(nullable = false)
    private StockStatus stockStatus;//库存状态

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

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "command_id")}, inverseJoinColumns = {@JoinColumn(name = "rep_command_id")})
    private Set<ReplenishmentCommand> commands;//补货指令

    public SaleOutboundHeader getHeader() {
        return header;
    }

    public void setHeader(SaleOutboundHeader header) {
        this.header = header;
    }

    public SaleOutboundDetail getDetail() {
        return detail;
    }

    public void setDetail(SaleOutboundDetail detail) {
        this.detail = detail;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
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

    public Set<ReplenishmentCommand> getCommands() {
        return commands;
    }

    public void setCommands(Set<ReplenishmentCommand> commands) {
        this.commands = commands;
    }
}
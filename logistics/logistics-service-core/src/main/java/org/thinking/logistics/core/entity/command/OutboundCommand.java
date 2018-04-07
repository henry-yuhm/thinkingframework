package org.thinking.logistics.core.entity.command;

import org.thinking.logistics.core.domain.support.AppendantSign;
import org.thinking.logistics.core.domain.support.InventoryState;
import org.thinking.logistics.core.entity.Batch;
import org.thinking.logistics.core.entity.Location;
import org.thinking.logistics.core.entity.bill.OutboundDetail;
import org.thinking.logistics.core.entity.bill.OutboundHeader;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class OutboundCommand extends Command {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batch batch;//批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState state;//库存状态

    @Column(nullable = false)
    private boolean activated = false;//是否激活

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creationQuantity = BigDecimal.ZERO;//创建数量

    @Column(nullable = false)
    private int creationPieces = 0;//创建件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creationRemainder = BigDecimal.ZERO;//创建余数

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

    @Column(nullable = false)
    private AppendantSign appendantSign = AppendantSign.ORIGINAL;//追加标识

    @Column(nullable = false)
    private String pickingOrder = "0";//拣货顺序

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "command_id"), inverseJoinColumns = @JoinColumn(name = "rep_command_id"))
    private Set<ReplenishingCommand> commands = new LinkedHashSet<>();//补货指令

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

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public InventoryState getState() {
        return state;
    }

    public void setState(InventoryState state) {
        this.state = state;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public BigDecimal getCreationQuantity() {
        return creationQuantity;
    }

    public void setCreationQuantity(BigDecimal creationQuantity) {
        this.creationQuantity = creationQuantity;
    }

    public int getCreationPieces() {
        return creationPieces;
    }

    public void setCreationPieces(int creationPieces) {
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

    public AppendantSign getAppendantSign() {
        return appendantSign;
    }

    public void setAppendantSign(AppendantSign appendantSign) {
        this.appendantSign = appendantSign;
    }

    public String getPickingOrder() {
        return pickingOrder;
    }

    public void setPickingOrder(String pickingOrder) {
        this.pickingOrder = pickingOrder;
    }

    public Set<ReplenishingCommand> getCommands() {
        return commands;
    }

    public void setCommands(Set<ReplenishingCommand> commands) {
        this.commands = commands;
    }
}
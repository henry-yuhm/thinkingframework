package org.jointown.logistics.core.entity;

import org.jointown.logistics.core.entity.container.Pallet;
import org.jointown.logistics.core.entity.support.StockState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
public class Stock {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batch batch;//批号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;//货位

    @Column(nullable = false)
    private StockState state;//库存状态

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity;//数量

    @Column(nullable = false)
    private BigInteger pieces;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainder;//余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal inboundQuantity;//入库预占数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal outboundQuantity;//出库预扣数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plusQuantity;//补货预占数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal minusQuantity;//补货预扣数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal transitionQuantity;//在途数量

    @Column(nullable = false)
    private boolean locking;//是否锁定

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal lockingQuantity;//锁定数量

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @Transient
    private BigDecimal availableOutboundQuantity;//可用出库数量

    @Transient
    private BigDecimal physicalOutboundQuantity;//物理出库数量

    @Transient
    private BigDecimal availableGrossQuantity;//可用总数量

    @Transient
    private BigDecimal availableQuantity;//可用数量

    @Transient
    private BigDecimal availableObtainQuantity;//可用索取数量

    @Transient
    private BigDecimal availableReplenishmentQuantity;//可用补货数量

    public Stock() {
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
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

    public StockState getState() {
        return state;
    }

    public void setState(StockState state) {
        this.state = state;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigInteger getPieces() {
        return pieces;
    }

    public void setPieces(BigInteger pieces) {
        this.pieces = pieces;
    }

    public BigDecimal getRemainder() {
        return remainder;
    }

    public void setRemainder(BigDecimal remainder) {
        this.remainder = remainder;
    }

    public BigDecimal getInboundQuantity() {
        return inboundQuantity;
    }

    public void setInboundQuantity(BigDecimal inboundQuantity) {
        this.inboundQuantity = inboundQuantity;
    }

    public BigDecimal getOutboundQuantity() {
        return outboundQuantity;
    }

    public void setOutboundQuantity(BigDecimal outboundQuantity) {
        this.outboundQuantity = outboundQuantity;
    }

    public BigDecimal getPlusQuantity() {
        return plusQuantity;
    }

    public void setPlusQuantity(BigDecimal plusQuantity) {
        this.plusQuantity = plusQuantity;
    }

    public BigDecimal getMinusQuantity() {
        return minusQuantity;
    }

    public void setMinusQuantity(BigDecimal minusQuantity) {
        this.minusQuantity = minusQuantity;
    }

    public BigDecimal getTransitionQuantity() {
        return transitionQuantity;
    }

    public void setTransitionQuantity(BigDecimal transitionQuantity) {
        this.transitionQuantity = transitionQuantity;
    }

    public boolean isLocking() {
        return locking;
    }

    public void setLocking(boolean locking) {
        this.locking = locking;
    }

    public BigDecimal getLockingQuantity() {
        return lockingQuantity;
    }

    public void setLockingQuantity(BigDecimal lockingQuantity) {
        this.lockingQuantity = lockingQuantity;
    }

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    public BigDecimal getAvailableOutboundQuantity() {
        return availableOutboundQuantity;
    }

    public void setAvailableOutboundQuantity(BigDecimal availableOutboundQuantity) {
        this.availableOutboundQuantity = availableOutboundQuantity;
    }

    public BigDecimal getPhysicalOutboundQuantity() {
        return physicalOutboundQuantity;
    }

    public void setPhysicalOutboundQuantity(BigDecimal physicalOutboundQuantity) {
        this.physicalOutboundQuantity = physicalOutboundQuantity;
    }

    public BigDecimal getAvailableGrossQuantity() {
        return availableGrossQuantity;
    }

    public void setAvailableGrossQuantity(BigDecimal availableGrossQuantity) {
        this.availableGrossQuantity = availableGrossQuantity;
    }

    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public BigDecimal getAvailableObtainQuantity() {
        return availableObtainQuantity;
    }

    public void setAvailableObtainQuantity(BigDecimal availableObtainQuantity) {
        this.availableObtainQuantity = availableObtainQuantity;
    }

    public BigDecimal getAvailableReplenishmentQuantity() {
        return availableReplenishmentQuantity;
    }

    public void setAvailableReplenishmentQuantity(BigDecimal availableReplenishmentQuantity) {
        this.availableReplenishmentQuantity = availableReplenishmentQuantity;
    }
}
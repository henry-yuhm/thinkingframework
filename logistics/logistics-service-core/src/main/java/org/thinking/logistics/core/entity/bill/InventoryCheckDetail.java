package org.thinking.logistics.core.entity.bill;

import org.thinking.logistics.core.entity.Location;
import org.thinking.logistics.core.entity.container.Pallet;
import org.thinking.logistics.core.entity.support.InventoryState;
import org.thinking.logistics.core.entity.support.TransferringReason;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class InventoryCheckDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState state;//库存状态

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    @Column(nullable = false)
    private int pieces = 0;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainder = BigDecimal.ZERO;//余数

    private String auditor;//审核员

    private TransferringReason reason;//移库原因

    public InventoryCheckDetail() {
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

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public BigDecimal getRemainder() {
        return remainder;
    }

    public void setRemainder(BigDecimal remainder) {
        this.remainder = remainder;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public TransferringReason getReason() {
        return reason;
    }

    public void setReason(TransferringReason reason) {
        this.reason = reason;
    }
}
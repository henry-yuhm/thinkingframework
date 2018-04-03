package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.Location;
import org.jointown.logistics.core.entity.container.Pallet;
import org.jointown.logistics.core.entity.support.StockState;
import org.jointown.logistics.core.entity.support.TransferringReason;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
public class TransferringDetail extends Detail {
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private TransferringHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location sourceLocation;//源货位

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location targetLocation;//目标货位

    @Column(nullable = false)
    private StockState sourceState;//源库存状态

    @Column(nullable = false)
    private StockState targetState;//目标库存状态

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity;//数量

    @Column(nullable = false)
    private BigInteger pieces;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainder;//余数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    private TransferringReason reason;//移库原因

    public TransferringDetail() {
    }

//    @Override
//    public TransferringHeader getHeader() {
//        return header;
//    }
//
//    public void setHeader(TransferringHeader header) {
//        this.header = header;
//    }

    public Location getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(Location sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public Location getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    public StockState getSourceState() {
        return sourceState;
    }

    public void setSourceState(StockState sourceState) {
        this.sourceState = sourceState;
    }

    public StockState getTargetState() {
        return targetState;
    }

    public void setTargetState(StockState targetState) {
        this.targetState = targetState;
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

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    public TransferringReason getReason() {
        return reason;
    }

    public void setReason(TransferringReason reason) {
        this.reason = reason;
    }
}
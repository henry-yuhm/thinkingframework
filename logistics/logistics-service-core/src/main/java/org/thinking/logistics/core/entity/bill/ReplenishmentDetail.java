package org.thinking.logistics.core.entity.bill;

import org.thinking.logistics.core.entity.Location;
import org.thinking.logistics.core.entity.container.Pallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class ReplenishmentDetail extends Detail {
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private ReplenishmentHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    private String storeCategory;//库别

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planQuantity = BigDecimal.ZERO;//计划数量

    @Column(nullable = false)
    private int planPieces = 0;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false)
    private int factPieces = 0;//实际件数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    public ReplenishmentDetail() {
    }

//    @Override
//    public ReplenishmentHeader getHeader() {
//        return header;
//    }
//
//    public void setHeader(ReplenishmentHeader header) {
//        this.header = header;
//    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStoreCategory() {
        return storeCategory;
    }

    public void setStoreCategory(String storeCategory) {
        this.storeCategory = storeCategory;
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

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }
}
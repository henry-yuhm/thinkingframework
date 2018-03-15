package org.jointown.logistics.stock.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

/**
 * Created by Henry on 2017/3/29.
 */
@Entity
@Table(name = "rec_invoicing")
public class Invoice {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JSONField(name = "Id")
    private long id;

    @Column(name = "owner_id")
    @JSONField(ordinal = 1,
            name = "OwnerId")
    private String ownerId;

    @Column(name = "bill_header_id")
    @JSONField(ordinal = 2,
            name = "bill_header_id")
    private String billHeaderId;

    @Column(name = "bill_detail_id")
    @JSONField(ordinal = 3,
            name = "bill_detail_id")
    private String billDetailId;

    @Column(name = "goods_id")
    @JSONField(ordinal = 4,
            name = "GoodsId")
    private String goodsId;

    @Column(name = "lot_id")
    @JSONField(ordinal = 5,
            name = "LotId")
    private String lotNoId;

    @Column(name = "stock_status")
    @JSONField(ordinal = 6,
            name = "StockStatus")
    private String stockStatus;

    @Column(name = "stock_quantity")
    @JSONField(ordinal = 7,
            name = "StockQuantity")
    private double stockQuantity;

    @Column(name = "changed_stock_quantity")
    @JSONField(ordinal = 8,
            name = "ChangedStockQuantity")
    private double stockChangedQuantity;

    @Column(name = "in_transit_quantity")
    @JSONField(ordinal = 9,
            name = "InTransitQuantity")
    private double inTransitQuantity;

    public Invoice() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getBillHeaderId() {
        return billHeaderId;
    }

    public void setBillHeaderId(String billHeaderId) {
        this.billHeaderId = billHeaderId;
    }

    public String getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(String billDetailId) {
        this.billDetailId = billDetailId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getLotNoId() {
        return lotNoId;
    }

    public void setLotNoId(String lotNoId) {
        this.lotNoId = lotNoId;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getStockChangedQuantity() {
        return stockChangedQuantity;
    }

    public void setStockChangedQuantity(double stockChangedQuantity) {
        this.stockChangedQuantity = stockChangedQuantity;
    }

    public double getInTransitQuantity() {
        return inTransitQuantity;
    }

    public void setInTransitQuantity(double inTransitQuantity) {
        this.inTransitQuantity = inTransitQuantity;
    }
}

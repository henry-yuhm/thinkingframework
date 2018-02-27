package org.jointown.logistics.stock.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StockEntityPk implements Serializable {
    //		private String ownerNo;
//
//		private String warehouseNo;
//
//		private String goodsId;
//
//		private String lotId;
//
//		private String locationId;
//
//		private String stockStatus;
    @Column(name = "owner_no")
    @JSONField(ordinal = 1,
            name = "owner_no",
            alternateNames = {"owner_no", "OWNER_NO", "YEZ_ID"})
    private String ownerNo;

    @Column(name = "warehouse_no")
    @JSONField(ordinal = 2,
            name = "warehouse_no",
            alternateNames = {"warehouse_no", "LC_CODE", "CANGK_NO"})
    private String warehouseNo;

    @Column(name = "goods_id")
    @JSONField(ordinal = 3,
            name = "goods_id",
            alternateNames = {"goods_id", "GOODS_ID", "SHANGP_ID", "SPID"})
    private String goodsId;

    @Column(name = "lot_id")
    @JSONField(ordinal = 4,
            name = "lot_id",
            alternateNames = {"lot_id", "LOTNO_ID", "PIH_ID", "PHID"})
    private String lotId;

    @Column(name = "location_id")
    @JSONField(ordinal = 5,
            name = "location_id",
            alternateNames = {"location_id", "LOCATION_ID", "HUOW_ID", "HWID"})
    private String locationId;

    @Column(name = "stock_status")
    @JSONField(ordinal = 6,
            name = "stock_status",
            alternateNames = {"stock_status", "STOCK_STATUS", "KUC_STATE"})
    private String stockStatus;

    public StockEntityPk() {
    }

    @Override
    public int hashCode() {
        return (this.ownerNo + this.warehouseNo + this.goodsId + this.lotId + this.stockStatus).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null) {
            return false;
        }

        if (this.getClass() != object.getClass()) {
            return false;
        }

        StockEntityPk stockEntityPk = (StockEntityPk) object;

        return (this.ownerNo.equals(stockEntityPk.ownerNo) &&
                this.warehouseNo.equals(stockEntityPk.warehouseNo) &&
                this.goodsId.equals(stockEntityPk.goodsId) &&
                this.lotId.equals(stockEntityPk.lotId) &&
                this.stockStatus.equals(stockEntityPk.stockStatus));
    }

    @Override
    public String toString() {
        return this.ownerNo + "_" + this.warehouseNo + "_" + this.goodsId + "_" + this.lotId + "_" + this.stockStatus;
    }

    public String getOwnerNo() {
        return ownerNo;
    }

    public void setOwnerNo(String ownerNo) {
        this.ownerNo = ownerNo;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }
}

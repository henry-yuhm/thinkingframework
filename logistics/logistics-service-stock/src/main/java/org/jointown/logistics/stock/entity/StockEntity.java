package org.jointown.logistics.stock.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Henry on 2017/3/30.
 */
@Entity
@Table(name = "fd_stock")
//@SecondaryTable(name = "fd_lot",pkJoinColumns = @PrimaryKeyJoinColumn(name = "lot_id",referencedColumnName = "lot_id",foreignKey = @ForeignKey))
@IdClass(StockEntity.StockEntityPk.class)
public class StockEntity implements Serializable {
    @Id
    @Column(name = "owner_no")
    @JSONField(ordinal = 1,
            name = "owner_no",
            alternateNames = {"owner_no", "OWNER_NO", "YEZ_ID"})
    private String ownerNo;

    @Id
    @Column(name = "warehouse_no")
    @JSONField(ordinal = 2,
            name = "warehouse_no",
            alternateNames = {"warehouse_no", "LC_CODE", "CANGK_NO"})
    private String warehouseNo;

    @Id
    @Column(name = "goods_id")
    @JSONField(ordinal = 3,
            name = "goods_id",
            alternateNames = {"goods_id", "GOODS_ID", "SHANGP_ID", "SPID"})
    private String goodsId;

    @Id
    @Column(name = "lot_id")
//	@JoinColumn(name = "lot_id",referencedColumnName = "lot_id",table = "fd_lot")
    @JSONField(ordinal = 4,
            name = "lot_id",
            alternateNames = {"lot_id", "LOTNO_ID", "PIH_ID", "PHID"})
    private String lotId;

    @Id
    @Column(name = "location_id")
    @JSONField(ordinal = 5,
            name = "location_id",
            alternateNames = {"location_id", "LOCATION_ID", "HUOW_ID", "HWID"})
    private String locationId;

    @Id
    @Column(name = "stock_status")
    @JSONField(ordinal = 6,
            name = "stock_status",
            alternateNames = {"stock_status", "STOCK_STATUS", "KUC_STATE"})
    private String stockStatus;

    @Column(name = "stock_quantity")
    @JSONField(ordinal = 7,
            name = "stock_quantity",
            alternateNames = {"stock_quantity", "STOCK_QTY", "NUM"})
    private double stockQuantity;

    @Column(name = "inbound_quantity")
    @JSONField(ordinal = 8,
            name = "inbound_quantity",
            alternateNames = {"inbound_quantity", "INSTOREHOUSE_PREPLUS_QTY", "YEZ_ID"})
    private double inboundQuantity;

    @Column(name = "outbound_quantity")
    @JSONField(ordinal = 9,
            name = "outbound_quantity",
            alternateNames = {"outbound_quantity", "OUTSTOREHOUSE_PREMINUS_QTY", "YEZ_ID"})
    private double outboundQuantity;

    @Column(name = "in_transit_quantity")
    @JSONField(ordinal = 10,
            name = "in_transit_quantity",
            alternateNames = {"in_transit_quantity", "INTRANSIT_QTY", "YEZ_ID"})
    private double inTransitQuantity;

    @Column(name = "pallet_no")
    @JSONField(ordinal = 11,
            name = "pallet_no",
            alternateNames = {"pallet_no", "PALLET_NO", "TUOP_BARCODE", "TPBH"})
    private String palletNo;

//    @OneToOne
//    @JoinColumn(name = "lot_id",
//            referencedColumnName = "lot_id")
//    private Lot lotEntity;

    public StockEntity() {
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

    public double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getInboundQuantity() {
        return inboundQuantity;
    }

    public void setInboundQuantity(double inboundQuantity) {
        this.inboundQuantity = inboundQuantity;
    }

    public double getOutboundQuantity() {
        return outboundQuantity;
    }

    public void setOutboundQuantity(double outboundQuantity) {
        this.outboundQuantity = outboundQuantity;
    }

    public double getInTransitQuantity() {
        return inTransitQuantity;
    }

    public void setInTransitQuantity(double inTransitQuantity) {
        this.inTransitQuantity = inTransitQuantity;
    }

    public String getPalletNo() {
        return palletNo;
    }

    public void setPalletNo(String palletNo) {
        this.palletNo = palletNo;
    }

//    public Lot getLotEntity() {
//        return lotEntity;
//    }
//
//    public void setLotEntity(Lot lotEntity) {
//        this.lotEntity = lotEntity;
//    }

    public static class StockEntityPk implements Serializable {
        private String ownerNo;

        private String warehouseNo;

        private String goodsId;

        private String lotId;

        private String locationId;

        private String stockStatus;

        public StockEntityPk() {
        }

        public String getLocationId() {
            return locationId;
        }

        public void setLocationId(String locationId) {
            this.locationId = locationId;
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

            return this.ownerNo.equals(stockEntityPk.getOwnerNo()) &&
                    this.warehouseNo.equals(stockEntityPk.getWarehouseNo()) &&
                    this.goodsId.equals(stockEntityPk.getGoodsId()) &&
                    this.lotId.equals(stockEntityPk.getLotId()) &&
                    this.stockStatus.equals(stockEntityPk.getStockStatus());
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

        public String getStockStatus() {
            return stockStatus;
        }

        public void setStockStatus(String stockStatus) {
            this.stockStatus = stockStatus;
        }
    }
}
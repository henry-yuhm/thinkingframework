package org.jointown.logistics.common.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fd_lot")
@Embeddable
public class LotEntity {
    @Id
    @Column(name = "lot_id")
    @JSONField(name = "lot_id",
            alternateNames = {"lot_id", "LOTNO_ID", "PIH_ID", "PHID"})
    private String lotId;

    @Column(name = "goods_id")
    @JSONField(ordinal = 1,
            name = "goods_id",
            alternateNames = {"goods_id", "GOODS_ID", "SHANGP_ID", "SPID"})
    private String goodsId;

    @Column(name = "lot_no")
    @JSONField(ordinal = 2,
            name = "lot_no",
            alternateNames = {"lot_no", "GOODS_LOTNO", "LOT", "PH"})
    private String lotNo;

    @Column(name = "production_date")
    @Temporal(TemporalType.DATE)
    @JSONField(ordinal = 3,
            name = "production_date",
            format = "yyyy-MM-dd",
            alternateNames = {"production_date", "PRODUCTION_DATE", "SHENGCHAN_DATE", "SCRQ"})
    private Date productionDate;

    @Column(name = "valid_until")
    @Temporal(TemporalType.DATE)
    @JSONField(ordinal = 4,
            name = "valid_until",
            format = "yyyy-MM-dd",
            alternateNames = {"valid_until", "VALID_UNTIL", "YOUX_DATE", "YXQ"})
    private Date validUntil;

    @Column(name = "print_production_date")
    @JSONField(ordinal = 5,
            name = "print_production_date",
            alternateNames = {"print_production_date", "PRINT_PRODUCTION_DATE", "YEZ_ID"})
    private String printProductionDate;

    @Column(name = "print_valid_until")
    @JSONField(ordinal = 6,
            name = "print_valid_until",
            alternateNames = {"print_valid_until", "PRINT_VALID_UNTIL", "YEZ_ID"})
    private String printValidUntil;

    @Column(name = "approval_no")
    @JSONField(ordinal = 7,
            name = "approval_no",
            alternateNames = {"approval_no", "APPROVAL_NO", "PIZ_NO", "PZWH"})
    private String approvalNo;

    @Column(name = "sterilization_lot_no")
    @JSONField(ordinal = 8,
            name = "sterilization_lot_no",
            alternateNames = {"sterilization_lot_no", "STERILIZATION_LOTNO", "MIEJ_LOT"})
    private String sterilizationLotNo;

    public LotEntity() {
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getPrintProductionDate() {
        return printProductionDate;
    }

    public void setPrintProductionDate(String printProductionDate) {
        this.printProductionDate = printProductionDate;
    }

    public String getPrintValidUntil() {
        return printValidUntil;
    }

    public void setPrintValidUntil(String printValidUntil) {
        this.printValidUntil = printValidUntil;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getSterilizationLotNo() {
        return sterilizationLotNo;
    }

    public void setSterilizationLotNo(String sterilizationLotNo) {
        this.sterilizationLotNo = sterilizationLotNo;
    }
}
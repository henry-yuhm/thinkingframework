package org.jointown.logistics.resource.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Henry on 2017/5/27.
 */
@Entity
@Table(name = "FD_CFG_DELIVERY_LIST")
@IdClass(DeliveryList.DeliveryListPk.class)
public class DeliveryList {
    @Id
    @Column(name = "CORP_NO",
            nullable = false)
    @JSONField(name = "CorporationNo",
            ordinal = 1)
    private String corporationNo;

    @Id
    @Column(name = "BILL_HDR_ID",
            nullable = false)
    @JSONField(name = "BillHeaderId",
            ordinal = 2)
    private String billHeaderId;

    @Column(name = "FILE_NAME",
            nullable = false)
    @JSONField(name = "FileName",
            ordinal = 3)
    private String fileName;

    public DeliveryList() {
    }

    public String getCorporationNo() {
        return corporationNo;
    }

    public void setCorporationNo(String corporationNo) {
        this.corporationNo = corporationNo;
    }

    public String getBillHeaderId() {
        return billHeaderId;
    }

    public void setBillHeaderId(String billHeaderId) {
        this.billHeaderId = billHeaderId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static class DeliveryListPk implements Serializable {
        private String corporationNo;

        private String billHeaderId;

        public DeliveryListPk() {
        }

        public String getCorporationNo() {
            return corporationNo;
        }

        public void setCorporationNo(String corporationNo) {
            this.corporationNo = corporationNo;
        }

        public String getBillHeaderId() {
            return billHeaderId;
        }

        public void setBillHeaderId(String billHeaderId) {
            this.billHeaderId = billHeaderId;
        }

        @Override
        public int hashCode() {
            return this.corporationNo.hashCode() + this.billHeaderId.hashCode();
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

            DeliveryListPk deliveryListPk = (DeliveryListPk) object;

            return this.corporationNo.equals(deliveryListPk.corporationNo) && this.billHeaderId.equals(deliveryListPk.billHeaderId);
        }

        @Override
        public String toString() {
            return this.corporationNo + "_" + this.billHeaderId;
        }
    }
}
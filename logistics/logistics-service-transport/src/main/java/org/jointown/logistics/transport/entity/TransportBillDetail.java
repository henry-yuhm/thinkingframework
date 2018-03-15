package org.jointown.logistics.transport.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Henry on 2017/5/27.
 */
@Entity
@Table(name = "Bill_TransportA_Dtl")
public class TransportBillDetail {
    @JSONField(name = "BillDetailId")
    @Id
    @Column(name = "Bill_Dtl_Id")
    private long billDetailId;

    @JSONField(name = "BillHeaderId",
            ordinal = 1)
    @Column(name = "Bill_Hdr_Id")
    private long billHeaderId;

    @JSONField(name = "DeliveryAddressId",
            ordinal = 2)
    @Column(name = "Delivery_Address_Id")
    private long deliveryAddressId;

    @JSONField(name = "CustomerId",
            ordinal = 3)
    @Column(name = "Ssa_Id")
    private long customerId;

    @JSONField(name = "GoodsId",
            ordinal = 4)
    @Column(name = "Goods_Id")
    private long goodsId;

    @JSONField(name = "PlannedPieces",
            ordinal = 5)
    @Column(name = "Planned_Pcs")
    private BigDecimal plannedPieces;

    @JSONField(name = "PlannedVolume",
            ordinal = 6)
    @Column(name = "Planned_Volume")
    private BigDecimal plannedVolume;

    @JSONField(name = "PlannedWeight",
            ordinal = 7)
    @Column(name = "Planned_Weight")
    private BigDecimal PlannedWeight;
}

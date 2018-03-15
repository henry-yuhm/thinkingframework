package org.jointown.logistics.transport.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Henry on 2017/5/27.
 */
@Entity
@Table(name = "Bill_Transport")
public class TransportBill {
    @JSONField(name = "BillHeaderId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Bill_Hdr_Id",
            nullable = false,
            updatable = false)
    private long billHeaderId;

    @JSONField(name = "SeqNo",
            ordinal = 1)
    @Column(name = "Seq_No")
    private BigDecimal seqNo;

    @JSONField(name = "OperatorId",
            ordinal = 2)
    @Column(name = "Operator_Id")
    private long operatorId;

    @JSONField(name = "ConsignorId",
            ordinal = 3)
    @OrderColumn(name = "Con_Id",
            nullable = false)
    private long consignorId;

    @JSONField(name = "BillTransportHeaderId",
            ordinal = 4)
    @Column(name = "BillTransportA_Hdr_Id")
    private long billTransportHeaderId;

    @JSONField(name = "BillTransportDetailId",
            ordinal = 5)
    @Column(name = "BillTransportA_Dtl_Id")
    private long billTransportDetailId;

    @JSONField(name = "ConsignorOrderNo",
            ordinal = 6)
    @Column(name = "ConOrder_no")
    private long consignorOrderNo;

    @JSONField(name = "TransportType",
            ordinal = 7)
    @Column(name = "Transport_Type")
    private String transportType;

    @JSONField(name = "AssignOperatorId",
            ordinal = 8)
    @Column(name = "AssignOperator_Id")
    private long assignOperatorId;

    @JSONField(name = "TransportCategory",
            ordinal = 9)
    @Column(name = "Transport_Category")
    private String transportCategory;

    @JSONField(name = "RouteId",
            ordinal = 10)
    @Column(name = "Route_Id")
    private long routeId;

    @JSONField(name = "StartAddressId",
            ordinal = 11)
    @Column(name = "Start_Address_Id")
    private long startAddressId;

    @JSONField(name = "EndAddressId",
            ordinal = 12)
    @Column(name = "End_Address_Id")
    private long endAddressId;

    @JSONField(name = "CustomerId",
            ordinal = 13)
    @Column(name = "Ssa_Id")
    private long customerId;

    @JSONField(name = "GoodsId",
            ordinal = 14)
    @Column(name = "Goods_Id")
    private long goodsId;

    @JSONField(name = "PlannedPieces",
            ordinal = 15)
    @Column(name = "Planned_Pcs")
    private BigDecimal plannedPieces;

    @JSONField(name = "PlannedVolume",
            ordinal = 16)
    @Column(name = "Planned_Volume")
    private BigDecimal plannedVolume;

    @JSONField(name = "PlannedWeight",
            ordinal = 17)
    @Column(name = "Planned_Weight")
    private BigDecimal PlannedWeight;

    @JSONField(name = "GoodsQuantity",
            ordinal = 18)
    @Column(name = "Goods_Num")
    private BigDecimal goodsQuantity;

    @JSONField(name = "GrossQuantity",
            ordinal = 19)
    @Column(name = "TotalBox_Num")
    private BigDecimal GrossQuantity;

    @JSONField(name = "FullloadQuantity",
            ordinal = 20)
    @Column(name = "Fullload_Num")
    private BigDecimal fullloadQuantity;

    @JSONField(name = "GroupageQuantity",
            ordinal = 21)
    @Column(name = "Groupage_Num")
    private BigDecimal groupageQuantity;

    @JSONField(name = "ChilledDrugQuantity",
            ordinal = 22)
    @Column(name = "Chilleddrug_Num")
    private BigDecimal chilledDrugQuantity;

    @JSONField(name = "ValuableDrugQuantity",
            ordinal = 23)
    @Column(name = "valuabledrug_Num")
    private BigDecimal valuableDrugQuantity;

    @JSONField(name = "ImportedDrugQuantity",
            ordinal = 24)
    @Column(name = "Importeddrug_Num")
    private BigDecimal importedDrugQuantity;

    @JSONField(name = "PsychotropicDrugQuantity",
            ordinal = 25)
    @Column(name = "PsychotropicDrug_Num")
    private BigDecimal psychotropicDrugQuantity;

    @JSONField(name = "SpecialControlDrugQuantity",
            ordinal = 26)
    @Column(name = "SpecialcontrolDrug_Num")
    private BigDecimal specialControlDrugQuantity;

    @JSONField(name = "OtherDrugQuantity",
            ordinal = 27)
    @Column(name = "OtherDrug_Num")
    private BigDecimal otherDrugQuantity;

    @JSONField(name = "GiftQuantity",
            ordinal = 28)
    @Column(name = "Gift_Num")
    private BigDecimal giftQuantity;

    @JSONField(name = "GrossVolume",
            ordinal = 29)
    @Column(name = "Gross_Volume")
    private BigDecimal grossVolume;

    @JSONField(name = "GrossWeight",
            ordinal = 30)
    @Column(name = "Gross_Weight")
    private BigDecimal grossWeight;

    @JSONField(name = "GrossAmount",
            ordinal = 31)
    @Column(name = "Gross_Amount")
    private BigDecimal grossAmount;

    @JSONField(name = "BillStatus",
            ordinal = 32)
    @Column(name = "Bill_State")
    private String billStatus;

    @JSONField(name = "TimeLimit",
            ordinal = 33)
    @Column(name = "Time_Limit")
    private BigDecimal timeLimit;
}

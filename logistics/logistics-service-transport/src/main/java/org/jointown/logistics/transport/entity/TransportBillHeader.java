package org.jointown.logistics.transport.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

/**
 * Created by Henry on 2017/5/27.
 */
@Entity
@Table(name = "Bill_TransportA_Hdr")
public class TransportBillHeader {
    @JSONField(name = "BillHeaderId")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Bill_Hdr_Id",
            nullable = false,
            updatable = false)
    private long billHeaderId;

    @JSONField(name = "OperatorId",
            ordinal = 1)
    @Column(name = "Operator_Id")
    private long operatorId;

    @JSONField(name = "ConsignorId",
            ordinal = 2)
    @OrderColumn(name = "Con_Id",
            nullable = false)
    private long consignorId;

    @JSONField(name = "TakeGoodsAddressId",
            ordinal = 3)
    @Column(name = "TdelAddress_Id")
    private long takeGoodsAddressId;
}

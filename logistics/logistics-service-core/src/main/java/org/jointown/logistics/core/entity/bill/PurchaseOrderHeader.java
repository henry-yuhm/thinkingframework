package org.jointown.logistics.core.entity.bill;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Entity
public class PurchaseOrderHeader extends OrderHeader {
    @Column(nullable = false)
    private Time invoiceTime;//开票时间

    private String buyer;//采购员

    private String contactName;//联系人

    private String contactPhone;//联系电话

    private String departureLocation;//发货地点

    private String shippingMode;//运输方式

    private String shipper;//运输方

    private Time shippingTime;//运输时间

    private String tempControlMode;//温控方式

    private String tempRecord;//温度记录

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "header_id")}, inverseJoinColumns = {@JoinColumn(name = "detail_id")})
    private Set<PurchaseOrderDetail> details;//单据明细

    public PurchaseOrderHeader() {
    }
}
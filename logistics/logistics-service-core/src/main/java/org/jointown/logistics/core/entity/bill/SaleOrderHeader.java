package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.support.SaleType;
import org.jointown.logistics.core.entity.support.TakegoodsMode;
import org.jointown.logistics.core.entity.support.TaxType;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Entity
public class SaleOrderHeader extends OrderHeader {
    @Column(nullable = false)
    private TakegoodsMode takegoodsMode;//提货方式

    @Column(nullable = false)
    private TakegoodsMode takegoodsModeSwitch;//提货方式转换

    @Column(nullable = false)
    private SaleType saleType;//销售类型

    private String deliveryType;//配送类型

    private boolean stockout;//是否缺货

    @Column(nullable = false)
    private Time invoiceTime;//开票时间

    private String settlementType;//结算类型

    private Time settlementTime;//结算时间

    private TaxType taxType;//税票类型

    private String taxName;//税票名称

    @Column(nullable = false)
    private boolean printContract;//是否打印合同

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "header_id")}, inverseJoinColumns = {@JoinColumn(name = "detail_id")})
    private Set<SaleOrderDetail> details;//单据明细
}
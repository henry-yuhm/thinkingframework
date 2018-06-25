package org.thinking.logistics.services.core.domain.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.core.Customer;
import org.thinking.logistics.services.core.domain.support.InboundType;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrderHeader extends Header {
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;//客户

    @Column(nullable = false)
    private boolean complete = false;//完成

    @Column(nullable = false)
    private InboundType inboundType;//入库类型

    @Column(nullable = false)
    private Instant invoiceTime;//开票时间

    private String buyer;//采购员

    private String contactName;//联系人

    private String contactPhone;//联系电话

    private String departureLocation;//发货地点

    private String shippingMode;//运输方式

    private String shipper;//运输方

    private Instant shippingTime;//运输时间

    private String tempControlMode;//温控方式

    private String tempRecord;//温度记录

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<PurchaseOrderDetail> details = new LinkedHashSet<>();//单据明细
}
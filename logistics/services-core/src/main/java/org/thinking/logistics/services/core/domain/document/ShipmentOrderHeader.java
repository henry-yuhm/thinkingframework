package org.thinking.logistics.services.core.domain.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.common.Address;
import org.thinking.logistics.services.core.domain.common.Customer;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.stagingarea.Stagingarea;
import org.thinking.logistics.services.core.domain.support.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ShipmentOrderHeader extends Header {
    @ManyToOne(fetch = FetchType.LAZY)
    private ShipmentOrderHeader parent;//父抬头

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;//客户

    @Column(nullable = false)
    private ShipmentStatus shipmentStatus;//发运状态

    @Column(nullable = false)
    private OutboundPriority outboundPriority;//出库优先级

    @Column(nullable = false)
    private PickupMode pickupMode;//提货方式

    @Column(nullable = false)
    private PickupMode pickupModeSwitch;//提货方式转换

    @Column(nullable = false)
    private SaleType saleType;//销售类型

    private String distributionType;//配送类型

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Instant invoiceTime;//开票时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee buyer;//采购员

    private String settlementType;//结算类型

    @Temporal(TemporalType.DATE)
    private Instant settlementTime;//结算时间

    private TaxType taxType;//税票类型

    private String taxName;//税票名称

    @Column(nullable = false)
    private boolean printContract;//打印合同

    private String wave;//波次

    @Column(nullable = false)
    private DispatcherType dispatcherType;//调度类型

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee dispatchers;//调度员

    @Temporal(TemporalType.DATE)
    private Instant dispatcherTime;//调度时间

    @Temporal(TemporalType.DATE)
    private Instant releaseTime;//下发时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;//地址

    @ManyToOne(fetch = FetchType.LAZY)
    private Stagingarea sourceStagingarea;//起始月台

    @ManyToOne(fetch = FetchType.LAZY)
    private Stagingarea targetStagingarea;//终止月台

    @Column(nullable = false)
    private boolean reversed;//整单冲红

    @Column(nullable = false)
    private boolean approved;//冲红审核

    @Column(nullable = false)
    private boolean uploaded;//上传

    @Column(nullable = false)
    private boolean gatheringCompleted;//集货完成

    @Temporal(TemporalType.DATE)
    private Instant taskCompleteTime;//作业完成时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee reviewer;//复核员

    @Temporal(TemporalType.DATE)
    private Instant reviewStartTime;//复核开始时间

    @Temporal(TemporalType.DATE)
    private Instant reviewCompleteTime;//复核结束时间

    @Column(nullable = false)
    private PrintSign reviewListPrintSign;//复核单打印标识

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee reviewListPrintClerk;//复核单打印员

    @Temporal(TemporalType.DATE)
    private Instant reviewListPrintTime;//复核单打印时间

    @Column(nullable = false)
    private PrintSign reportListPrintSign;//报告单打印标识

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee reportListPrintClerk;//报告单打印员

    @Temporal(TemporalType.DATE)
    private Instant reportListPrintTime;//报告单打印时间

    @Column(nullable = false)
    private int printTimes = 0;//打印次数

    @Column(nullable = false)
    private boolean stagingareaEmpty;//清空月台

    @Column(nullable = false)
    private int itemQuantity = 0;//品规数

    @Column(nullable = false)
    private BigDecimal equivalentCases;//折合件数

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<ShipmentOrderDetail> details;//单据明细

    public void isResended() throws Exception {
        if (this.shipmentStatus.compareTo(ShipmentStatus.RESEND) >= 0 && this.getNo().isEmpty()) {
            throw CompositeException.getException("单据已经补发", this, this.getOwner());
        }
    }
}
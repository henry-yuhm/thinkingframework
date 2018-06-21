package org.thinking.logistics.services.core.domain.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.core.Address;
import org.thinking.logistics.services.core.domain.core.Customer;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.stagingarea.Stagingarea;
import org.thinking.logistics.services.core.domain.support.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ShipmentOrderHeader extends Header {
    @ManyToOne(fetch = FetchType.LAZY)
    private ShipmentOrderHeader parent;//父抬头

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;//客户

    @Column(nullable = false)
    private OutboundStage stage = OutboundStage.CREATED;//出库阶段

    @Column(nullable = false)
    private OutboundPriority priority;//出库优先级

    @Column(nullable = false)
    private PickupMode pickupMode;//提货方式

    @Column(nullable = false)
    private PickupMode pickupModeSwitch;//提货方式转换

    @Column(nullable = false)
    private SaleType saleType;//销售类型

    private String distributionType;//配送类型

    @Column(nullable = false)
    private Date invoiceTime;//开票时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee buyer;//采购员

    private String settlementType = "无";//结算类型

    private Date settlementTime;//结算时间

    private TaxType taxType;//税票类型

    private String taxName;//税票名称

    @Column(nullable = false)
    private boolean printContract = false;//打印合同

    private String wave;//波次

    @Column(nullable = false)
    private DispatcherType dispatcherType = DispatcherType.AUTOMATIC;//调度类型

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee dispatchers;//调度员

    private Date dispatcherTime;//调度时间

    private Date releaseTime;//下发时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;//地址

    @ManyToOne(fetch = FetchType.LAZY)
    private Stagingarea sourceStagingarea;//起始月台

    @ManyToOne(fetch = FetchType.LAZY)
    private Stagingarea targetStagingarea;//终止月台

    @Column(nullable = false)
    private boolean inversed = false;//整单冲红

    @Column(nullable = false)
    private boolean approved = false;//冲红审核

    @Column(nullable = false)
    private boolean uploaded = false;//上传

    @Column(nullable = false)
    private boolean gatheringComplete = false;//集货完成

    private Date taskCompleteTime;//作业完成时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee rechecker;//复核员

    private Date recheckStartTime;//复核开始时间

    private Date recheckCompleteTime;//复核结束时间

    @Column(nullable = false)
    private PrintSign recheckbillPrintSign = PrintSign.NONE;//复核单打印标识

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee recheckbillPrintClerk;//复核单打印员

    private Date recheckbillPrintTime;//复核单打印时间

    @Column(nullable = false)
    private PrintSign reportbillPrintSign = PrintSign.NONE;//报告单打印标识

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee reportbillPrintClerk;//报告单打印员

    private Date reportbillPrintTime;//报告单打印时间

    @Column(nullable = false)
    private int printTimes = 0;//打印次数

    @Column(nullable = false)
    private boolean stagingareaCleaned = false;//清空月台

    @Column(nullable = false)
    private int itemQuantity = 0;//品规数

    @Column(nullable = false)
    private BigDecimal equivalentPieces = BigDecimal.ZERO;//折合件数

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<ShipmentOrderDetail> details = new LinkedHashSet<>();//单据明细

    public void isResended() throws Exception {
        if (this.stage.compareTo(OutboundStage.RESEND) >= 0 && this.getNo().isEmpty()) {
            throw CompositeException.getException("单据已经补发", this, this.getOwner());
        }
    }
}
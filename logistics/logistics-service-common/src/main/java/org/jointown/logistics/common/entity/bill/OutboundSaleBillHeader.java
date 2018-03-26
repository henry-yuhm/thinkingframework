package org.jointown.logistics.common.entity.bill;

import org.jointown.logistics.common.entity.Address;
import org.jointown.logistics.common.entity.StagingArea;
import org.jointown.logistics.common.entity.support.DispatchType;
import org.jointown.logistics.common.entity.support.SaleType;
import org.jointown.logistics.common.entity.support.TakeMode;
import org.jointown.logistics.common.entity.support.TaxTicketType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.sql.Time;
import java.util.Set;

@Entity
public class OutboundSaleBillHeader extends OutboundBillHeader {
    private TakeMode takeMode;//提货方式

    private TakeMode takeModeConvertor;//提货方式转换

    private SaleType saleType;//销售类型

    private String waveNo;//波次号

    private DispatchType dispatchType;//调度类型

    private String dispatcher;//调度员

    private Time dispatchTime;//调度时间

    private Time releaseTime;//下发时间

    private String deliveryType;//配送类型

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;//地址

    @OneToOne(fetch = FetchType.LAZY)
    private StagingArea sourceStagingArea;//起始月台

    @OneToOne(fetch = FetchType.LAZY)
    private StagingArea targetStagingArea;//终止月台

    private boolean stockout;//是否缺货

    private Time invoiceTime;//开票时间

    private String settleupType;//结算类型

    private Time settleupTime;//结算时间

    private TaxTicketType taxTicketType;//税票类型

    private String taxTicketName;//税票名称

    private boolean printContract;//是否打印合同

    private String recheckBillPrintSign;//复核单打印标识

    private Time recheckBillPrintTime;//复核单打印时间

    private String reportBillPrintSign;//报告单打印标识

    private String reportBillPrintClerk;//报告单打印员

    @ManyToMany
    private Set<OutboundSaleBillDetail> billDetails;//单据明细
}
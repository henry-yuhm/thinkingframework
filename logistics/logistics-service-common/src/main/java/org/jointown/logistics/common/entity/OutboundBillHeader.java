package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "billDetails", joinTable = @JoinTable(foreignKey = @ForeignKey(name = "fk_obh_bd_obh"), inverseForeignKey = @ForeignKey(name = "fk_obh_bd_obd")))
})
public class OutboundBillHeader extends BillHeader {
    private TakeMode takeMode;//提货方式

    private TakeMode takeModeConvertor;//提货方式转换

    private BillPriority billPriority;//单据优先级

    private SaleType saleType;//销售类型

    private String waveNo;//波次号

    private String dispatchType;//调度类型

    private String dispatcher;//调度员

    private Date dispatchingTime;//调度时间

    private Date releaseTime;//下发时间

    private String deliveryType;//配送类型

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obh_address"))
    private Address address;//地址

    private String businessman;//业务员

    private String buyer;//采购员

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obh_source_sga"))
    private StagingArea sourceStagingArea;//起始月台

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obh_target_sga"))
    private StagingArea targetStagingArea;//终止月台

    private boolean stockout;//是否缺货

    private Date invoiceTime;//开票时间

    private String settleupType;//结算类型

    private Date settleupTime;//结算时间

    private boolean wholeBillReversed;//是否整单冲红

    private DownloadSide downloadSide;//下传方

    private TaxTicketType taxTicketType;//税票类型

    private String taxTicketName;//税票名称

    private boolean printContract;//是否打印合同

    private boolean reversionAudit;//是否冲红审核

    private boolean collectionComplete;//是否集货完成

    private Date operationCompleteTime;//作业完成时间

    private String rechecker;//复核员

    private Date recheckStartTime;//复核开始时间

    private Date recheckCompleteTime;//复核结束时间

    private String recheckBillPrintSign;//复核单打印标识

    private Date recheckBillPrintTime;//复核单打印时间

    private String reportBillPrintSign;//报告单打印标识

    private String reportBillPrintClerk;//报告单打印员

    private boolean stagingAreaCleaned;//是否清空月台
}
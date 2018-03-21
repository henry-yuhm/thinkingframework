package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "billDetails", joinTable = @JoinTable(foreignKey = @ForeignKey(name = "fk_obh_bd_obh"), inverseForeignKey = @ForeignKey(name = "fk_obh_bd_obd")))
})
public class OutboundBillHeader extends BillHeader {
    private TakeMode takeMode;

    private TakeMode takeModeConvertor;

    private BillPriority billPriority;

    private SaleType saleType;

    private String waveNo;

    private String dispatchType;

    private String dispatcher;

    private Date dispatchingTime;

    private Date releaseTime;

    private String deliveryType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obh_address"))
    private Address address;

    private String businessman;

    private String buyer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obh_source_sga"))
    private StagingArea sourceStagingArea;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obh_target_sga"))
    private StagingArea targetStagingArea;

    private boolean stockout;

    private Date invoiceTime;

    private String settleupType;

    private Date settleupTime;

    private boolean wholeBillReversed;

    private DownloadSide downloadSide;

    private TaxTicketType taxTicketType;

    private String taxTicketName;

    private boolean printContract;

    private boolean reversionAudit;

    private boolean collectionComplete;

    private Date innerRecheckCompleteTime;

    private Date outerRecheckCompleteTime;

    private String recheckBillPrintSign;

    private Date recheckBillPrintTime;

    private String reportBillPrintSign;

    private String reportBillPrintClerk;

    private boolean stagingAreaCleaned;
}
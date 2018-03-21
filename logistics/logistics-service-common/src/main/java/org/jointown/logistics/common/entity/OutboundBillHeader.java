package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OutboundBillHeader extends BusinessBillHeader {
    private String waveNo;

    private String dispatchingType;

    private String dispatcher;

    private Date dispatchingTime;

    private Date releaseTime;

    private TakeMode takeMode;

    private TakeMode takeModeConvertor;

    private Priority priority;

    private SaleType saleType;

    private String deliveryType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_address"))
    private Address address;

    private String businessman;

    private String buyer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obh_source_staging_area"))
    private StagingArea sourceStagingArea;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obh_target_staging_area"))
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
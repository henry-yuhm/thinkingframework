package org.jointown.logistics.common.entity.bill;

import org.jointown.logistics.common.entity.support.OutboundBillStage;

import javax.persistence.MappedSuperclass;
import java.sql.Time;

@MappedSuperclass
public abstract class OutboundBillHeader extends BillHeader {
    private OutboundBillStage stage;//单据阶段

    private boolean wholeBillReversed;//是否整单冲红

    private boolean reversionAudit;//是否冲红审核

    private boolean uploaded;//是否上传

    private boolean collectionComplete;//是否集货完成

    private Time operationCompleteTime;//作业完成时间

    private String rechecker;//复核员

    private Time recheckStartTime;//复核开始时间

    private Time recheckCompleteTime;//复核结束时间

    private boolean stagingAreaCleaned;//是否清空月台

    private String businessman;//业务员

    private String buyer;//采购员
}
package org.jointown.logistics.common.entity.command;

import org.jointown.logistics.common.entity.bill.OutboundSaleBillDetail;
import org.jointown.logistics.common.entity.bill.OutboundSaleBillHeader;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class InboundCommand extends Command {
    @OneToOne(fetch = FetchType.LAZY)
    private InboundCommand parent;//父指令

    @OneToOne(fetch = FetchType.LAZY)
    private OutboundSaleBillHeader billHeader;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    private OutboundSaleBillDetail billDetail;//单据明细
}
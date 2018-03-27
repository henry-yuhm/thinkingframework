package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.bill.OutboundSaleBillDetail;
import org.jointown.logistics.core.entity.bill.OutboundSaleBillHeader;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class InboundCommand extends Command {
    @OneToOne(fetch = FetchType.LAZY)
    private InboundCommand parent;//父指令

    @OneToOne(fetch = FetchType.LAZY)
    private OutboundSaleBillHeader header;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    private OutboundSaleBillDetail detail;//单据明细
}
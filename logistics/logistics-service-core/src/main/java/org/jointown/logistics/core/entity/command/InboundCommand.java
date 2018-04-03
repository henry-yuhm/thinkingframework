package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.bill.InboundDetail;
import org.jointown.logistics.core.entity.bill.InboundHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class InboundCommand extends Command {
    @ManyToOne(fetch = FetchType.LAZY)
    private InboundCommand parent;//父指令

    @ManyToOne(fetch = FetchType.LAZY)
    private InboundHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY)
    private InboundDetail detail;//单据明细

    public InboundCommand() {
    }
}
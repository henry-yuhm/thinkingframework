package org.thinking.logistics.services.core.entity.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.entity.bill.InboundDetail;
import org.thinking.logistics.services.core.entity.bill.InboundHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class InboundCommand extends Command {
    @ManyToOne(fetch = FetchType.LAZY)
    private InboundCommand parent;//父指令

    @ManyToOne(fetch = FetchType.LAZY)
    private InboundHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY)
    private InboundDetail detail;//单据明细
}
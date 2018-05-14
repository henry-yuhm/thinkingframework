package org.thinking.logistics.services.core.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.documents.InboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.InboundOrderHeader;

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
    private InboundOrderHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY)
    private InboundOrderDetail detail;//单据明细
}
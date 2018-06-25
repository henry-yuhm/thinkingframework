package org.thinking.logistics.services.core.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.document.InboundOrderDetail;
import org.thinking.logistics.services.core.domain.document.InboundOrderHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
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
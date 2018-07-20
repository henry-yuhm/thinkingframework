package org.thinking.sce.service.core.domain.command;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.document.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ReceivingCommand extends Command {
    @ManyToOne(fetch = FetchType.LAZY)
    private ReceivingCommand parent;//父指令

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReceivingNoteHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReceivingNoteDetail detail;//单据明细
}
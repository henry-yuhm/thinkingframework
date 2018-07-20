package org.thinking.sce.service.core.domain.command;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.container.Pallet;
import org.thinking.sce.service.core.domain.document.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class TransferCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TransferOrderHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TransferOrderDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘
}
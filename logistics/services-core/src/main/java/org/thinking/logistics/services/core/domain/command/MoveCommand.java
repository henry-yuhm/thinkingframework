package org.thinking.logistics.services.core.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.document.MoveOrderDetail;
import org.thinking.logistics.services.core.domain.document.MoveOrderHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class MoveCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MoveOrderHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MoveOrderDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘
}
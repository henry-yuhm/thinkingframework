package org.thinking.logistics.core.entity.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.entity.bill.TransferringDetail;
import org.thinking.logistics.core.entity.bill.TransferringHeader;
import org.thinking.logistics.core.entity.container.Pallet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class TransferringCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TransferringHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TransferringDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘
}
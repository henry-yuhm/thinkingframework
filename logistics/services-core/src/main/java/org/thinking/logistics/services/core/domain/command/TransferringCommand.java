package org.thinking.logistics.services.core.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.document.TransferringDocumentDetail;
import org.thinking.logistics.services.core.domain.document.TransferringDocumentHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class TransferringCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TransferringDocumentHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TransferringDocumentDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘
}
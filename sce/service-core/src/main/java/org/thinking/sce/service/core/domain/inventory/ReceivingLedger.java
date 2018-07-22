package org.thinking.sce.service.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.document.Header;
import org.thinking.sce.service.core.domain.document.ReceivingNoteHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ReceivingLedger extends Ledger {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReceivingNoteHeader header;

    @Override
    public void setHeader(Header header) {
        this.header = (ReceivingNoteHeader) header;
    }
}
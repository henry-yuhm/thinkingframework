package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.document.Header;
import org.thinking.logistics.services.core.domain.document.ReplenishmentDocumentHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ReplenishmentDocumentLedger extends Ledger {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReplenishmentDocumentHeader header;

    @Override
    public void setHeader(Header header) {
        this.header = (ReplenishmentDocumentHeader) header;
    }
}
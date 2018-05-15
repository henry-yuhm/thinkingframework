package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.documents.Header;
import org.thinking.logistics.services.core.domain.documents.ReplenishingDocumentsHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ReplenishingDocumentsLedger extends Ledger {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReplenishingDocumentsHeader header;

    @Override
    public void setHeader(Header header) {
        this.header = (ReplenishingDocumentsHeader) header;
    }
}
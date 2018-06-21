package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.document.Header;
import org.thinking.logistics.services.core.domain.document.InventoryDocumentHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class InventoryDocumentLedger extends Ledger {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InventoryDocumentHeader header;

    @Override
    public void setHeader(Header header) {
        this.header = (InventoryDocumentHeader) header;
    }
}
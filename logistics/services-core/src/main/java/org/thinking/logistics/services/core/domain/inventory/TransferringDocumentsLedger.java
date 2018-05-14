package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.documents.TransferringDocumentsHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class TransferringDocumentsLedger extends Ledger {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TransferringDocumentsHeader header;
}
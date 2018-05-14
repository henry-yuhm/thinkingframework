package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class OutboundOrderLedger extends Ledger {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundOrderHeader header;
}
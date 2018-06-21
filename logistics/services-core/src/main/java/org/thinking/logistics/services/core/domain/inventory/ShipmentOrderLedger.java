package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.document.Header;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ShipmentOrderLedger extends Ledger {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ShipmentOrderHeader header;

    @Override
    public void setHeader(Header header) {
        this.header = (ShipmentOrderHeader) header;
    }
}
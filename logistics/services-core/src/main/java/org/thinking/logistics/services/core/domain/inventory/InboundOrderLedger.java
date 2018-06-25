package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.document.Header;
import org.thinking.logistics.services.core.domain.document.InboundOrderHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class InboundOrderLedger extends Ledger {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InboundOrderHeader header;

    @Override
    public void setHeader(Header header) {
        this.header = (InboundOrderHeader) header;
    }
}
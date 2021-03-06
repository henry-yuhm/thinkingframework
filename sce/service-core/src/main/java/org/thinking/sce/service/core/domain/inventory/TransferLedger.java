package org.thinking.sce.service.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.document.Header;
import org.thinking.sce.service.core.domain.document.TransferOrderHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class TransferLedger extends Ledger {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TransferOrderHeader header;

    @Override
    public void setHeader(Header header) {
        this.header = (TransferOrderHeader) header;
    }
}
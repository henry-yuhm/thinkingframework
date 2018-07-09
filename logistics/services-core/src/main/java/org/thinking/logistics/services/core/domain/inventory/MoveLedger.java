package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.document.Header;
import org.thinking.logistics.services.core.domain.document.MoveOrderHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class MoveLedger extends Ledger {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MoveOrderHeader header;

    @Override
    public void setHeader(Header header) {
        this.header = (MoveOrderHeader) header;
    }
}
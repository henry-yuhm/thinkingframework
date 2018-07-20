package org.thinking.sce.service.core.domain.inventory;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.document.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ShipmentLedger extends Ledger {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ShipmentOrderHeader header;

    @Override
    public void setHeader(Header header) {
        this.header = (ShipmentOrderHeader) header;
    }
}
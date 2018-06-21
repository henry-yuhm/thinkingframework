package org.thinking.logistics.services.core.domain.scheduler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderScheduler extends Scheduler {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ShipmentOrderHeader header;//单据抬头
}
package org.thinking.sce.service.core.domain.scheduler;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderScheduler extends Scheduler {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ShipmentOrderHeader header;//单据抬头
}
package org.thinking.sce.service.core.domain.scheduler;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.barcode.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class BarcodeScheduler extends Scheduler {
    @OneToOne(fetch = FetchType.LAZY)
    private ShipmentBarcode shipmentBarcode;//出库条码

    @OneToOne(fetch = FetchType.LAZY)
    private ReplenishmentBarcode replenishmentBarcode;//补货条码
}
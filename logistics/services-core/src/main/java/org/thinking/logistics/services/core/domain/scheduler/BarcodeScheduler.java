package org.thinking.logistics.services.core.domain.scheduler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.barcode.ReplenishmentBarcode;
import org.thinking.logistics.services.core.domain.barcode.ShipmentBarcode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

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
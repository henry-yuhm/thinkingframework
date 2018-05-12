package org.thinking.logistics.services.core.domain.scheduler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.barcode.OutboundBarcode;
import org.thinking.logistics.services.core.domain.barcode.ReplenishingBarcode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class BarcodeScheduler extends Scheduler {
    @OneToOne(fetch = FetchType.LAZY)
    private OutboundBarcode outboundBarcode;//出库条码

    @OneToOne(fetch = FetchType.LAZY)
    private ReplenishingBarcode replenishingBarcode;//补货条码
}
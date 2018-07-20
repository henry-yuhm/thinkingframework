package org.thinking.sce.order.initializer.domain;

import lombok.*;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.support.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnInitiator extends AbstractInitiator {
    public PurchaseReturnInitiator(ShipmentOrderHeader header) {
        super(header);
    }

    @Override
    public void save() throws Exception {
        this.getHeader().setOutboundPriority(OutboundPriority.PURCHASE_RETURN);
        this.getHeader().setPickupMode(PickupMode.NONE);
        this.getHeader().setPickupModeSwitch(PickupMode.NONE);

        super.save();
    }
}
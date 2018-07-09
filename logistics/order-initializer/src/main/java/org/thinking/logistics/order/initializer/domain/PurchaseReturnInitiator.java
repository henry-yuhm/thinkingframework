package org.thinking.logistics.order.initializer.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.support.OutboundPriority;
import org.thinking.logistics.services.core.domain.support.PickupMode;

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
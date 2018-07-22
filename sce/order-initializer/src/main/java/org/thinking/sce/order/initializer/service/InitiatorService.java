package org.thinking.sce.order.initializer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.sce.order.initializer.domain.Initiator;
import org.thinking.sce.order.initializer.domain.PurchaseReturnInitiator;
import org.thinking.sce.order.initializer.domain.SaleOutboundInitiator;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.support.SaleType;
import org.thinking.sce.service.core.service.document.ShipmentOrderService;

@Service
public class InitiatorService {
    private ShipmentOrderService orderService;

    @Autowired
    public InitiatorService(ShipmentOrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void initialize(long id) throws Exception {
        ShipmentOrderHeader header = this.orderService.acquire(id, true);

        Initiator initiator = header.getSaleType() == SaleType.PURCHASE_RETURN ? new PurchaseReturnInitiator(header) : new SaleOutboundInitiator(header);

        initiator.init();
    }
}
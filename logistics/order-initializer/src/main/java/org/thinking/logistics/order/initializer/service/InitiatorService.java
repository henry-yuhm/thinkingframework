package org.thinking.logistics.order.initializer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.order.initializer.domain.Initiator;
import org.thinking.logistics.order.initializer.domain.PurchaseReturnInitiator;
import org.thinking.logistics.order.initializer.domain.SaleOutboundInitiator;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.service.documents.OutboundOrderService;

@Service
public class InitiatorService {
    private OutboundOrderService orderService;

    @Autowired
    public InitiatorService(OutboundOrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void initialize(long id) throws Exception {
        OutboundOrderHeader header = this.orderService.acquire(id, true);

        Initiator initiator = header.getSaleType() == SaleType.PURCHASE_RETURN ? new PurchaseReturnInitiator(header) : new SaleOutboundInitiator(header);

        initiator.init();
    }
}
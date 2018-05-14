package org.thinking.logistics.order.initialization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.order.initialization.domain.Initializer;
import org.thinking.logistics.order.initialization.domain.PurchaseReturnInitializer;
import org.thinking.logistics.order.initialization.domain.SaleOutboundInitializer;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.service.documents.OutboundOrderService;

@Service
public class InitializerService {
    private OutboundOrderService orderService;

    @Autowired
    public InitializerService(OutboundOrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void initialize(long id) throws Exception {
        OutboundOrderHeader header = this.orderService.acquire(id, true);

        Initializer initializer = header.getSaleType() == SaleType.PURCHASE_RETURN ? new PurchaseReturnInitializer(header) : new SaleOutboundInitializer(header);

        initializer.initialize();
    }
}
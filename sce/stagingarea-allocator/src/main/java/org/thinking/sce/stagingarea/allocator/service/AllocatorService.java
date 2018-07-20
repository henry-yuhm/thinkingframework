package org.thinking.sce.stagingarea.allocator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.support.SaleType;
import org.thinking.sce.service.core.service.document.ShipmentOrderService;
import org.thinking.sce.stagingarea.allocator.domain.*;

@Service
public class AllocatorService {
    private ShipmentOrderService orderService;

    @Autowired
    public AllocatorService(ShipmentOrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void allocate(long id) throws Exception {
        ShipmentOrderHeader header = this.orderService.acquire(id, true);

        Allocator allocator = header.getSaleType() == SaleType.PURCHASE_RETURN ? new PurchaseReturnAllocator(header) : new SaleOutboundAllocator(header);

        allocator.allocate();
    }
}
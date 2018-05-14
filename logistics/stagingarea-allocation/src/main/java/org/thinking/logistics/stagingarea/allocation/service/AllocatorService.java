package org.thinking.logistics.stagingarea.allocation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.service.documents.OutboundOrderService;
import org.thinking.logistics.stagingarea.allocation.domain.Allocator;
import org.thinking.logistics.stagingarea.allocation.domain.PurchaseReturnAllocator;
import org.thinking.logistics.stagingarea.allocation.domain.SaleOutboundAllocator;

@Service
public class AllocatorService {
    private OutboundOrderService orderService;

    @Autowired
    public AllocatorService(OutboundOrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void allocate(long id) throws Exception {
        OutboundOrderHeader header = this.orderService.acquire(id, true);

        Allocator allocator = header.getSaleType() == SaleType.PURCHASE_RETURN ? new PurchaseReturnAllocator(header) : new SaleOutboundAllocator(header);

        allocator.allocate();
    }
}
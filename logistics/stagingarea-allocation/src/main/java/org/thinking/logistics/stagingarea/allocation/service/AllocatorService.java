package org.thinking.logistics.stagingarea.allocation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.repository.bill.OutboundHeaderRepository;
import org.thinking.logistics.stagingarea.allocation.domain.Allocator;
import org.thinking.logistics.stagingarea.allocation.domain.PurchaseReturnAllocator;
import org.thinking.logistics.stagingarea.allocation.domain.SaleOutboundAllocator;

@Service
public class AllocatorService {
    private OutboundHeaderRepository headerRepository;

    public AllocatorService(OutboundHeaderRepository headerRepository) {
        this.headerRepository = headerRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void allocate(long id) throws Exception {
        Allocator allocator;

        OutboundHeader header = this.headerRepository.getOne(id);

        if (header.getSaleType() == SaleType.PURCHASE_RETURN) {
            allocator = new PurchaseReturnAllocator(header);
        } else {
            allocator = new SaleOutboundAllocator(header);
        }

        allocator.allocate();
    }
}
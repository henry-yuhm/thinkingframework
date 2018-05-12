package org.thinking.logistics.order.initialization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.order.initialization.domain.Initializer;
import org.thinking.logistics.order.initialization.domain.PurchaseReturnInitializer;
import org.thinking.logistics.order.initialization.domain.SaleOutboundInitializer;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.repository.bill.OutboundHeaderRepository;

@Service
public class InitializerService {
    private OutboundHeaderRepository headerRepository;

    @Autowired
    public InitializerService(OutboundHeaderRepository headerRepository) {
        this.headerRepository = headerRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void initialize(long id) throws Exception {
        OutboundHeader header = this.headerRepository.getOne(id);

        Initializer initializer = header.getSaleType() == SaleType.PURCHASE_RETURN ? new PurchaseReturnInitializer(header) : new SaleOutboundInitializer(header);

        initializer.initialize();
    }
}
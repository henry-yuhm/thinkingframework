package org.thinking.logistics.init.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.core.domain.BusinessAdapter;
import org.thinking.logistics.core.domain.support.SaleKind;
import org.thinking.logistics.core.entity.bill.OutboundHeader;
import org.thinking.logistics.core.repository.OutboundHeaderRepository;
import org.thinking.logistics.init.domain.Initializer;
import org.thinking.logistics.init.domain.OutboundSaleInitializer;
import org.thinking.logistics.init.domain.ReturnedPurchaseInitializer;

@Service
public class InitializerService extends BusinessAdapter {
    private OutboundHeaderRepository headerRepository;

    @Autowired
    public InitializerService(OutboundHeaderRepository headerRepository) {
        this.headerRepository = headerRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void initialize(long id) throws Exception {
        Initializer initializer;

        OutboundHeader header = this.headerRepository.getOne(id);

        if (header.getSaleKind() == SaleKind.PURCHASE_RETURN) {
            initializer = new ReturnedPurchaseInitializer(header);
        } else {
            initializer = new OutboundSaleInitializer(header);
        }

        initializer.initialize();
    }
}
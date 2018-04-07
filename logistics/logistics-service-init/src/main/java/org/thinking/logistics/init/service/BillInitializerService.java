package org.thinking.logistics.init.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.core.domain.support.SaleType;
import org.thinking.logistics.core.entity.bill.OutboundHeader;
import org.thinking.logistics.core.repository.HeaderRepository;
import org.thinking.logistics.init.domain.BillInitializer;
import org.thinking.logistics.init.domain.OutboundSaleBillInitializer;
import org.thinking.logistics.init.domain.ReturnedPurchaseBillInitializer;

@Service
public class BillInitializerService {
    @Autowired
    private HeaderRepository<OutboundHeader> repository;

    @Transactional(rollbackFor = Exception.class)
    public void initialize(long id) throws Exception {
        BillInitializer billInitializer;

        OutboundHeader header = this.repository.findOne(id);

        if (header.getSaleType() == SaleType.RETURNED_PURCHASE) {
            billInitializer = new ReturnedPurchaseBillInitializer(this.repository, header);
        } else {
            billInitializer = new OutboundSaleBillInitializer(this.repository, header);
        }

        billInitializer.initialize();
    }
}
package org.jointown.logistics.init.service;

import org.jointown.logistics.core.entity.bill.OutboundHeader;
import org.jointown.logistics.core.entity.support.SaleType;
import org.jointown.logistics.core.repository.HeaderRepository;
import org.jointown.logistics.init.domain.BillInitializer;
import org.jointown.logistics.init.domain.OutboundSaleBillInitializer;
import org.jointown.logistics.init.domain.ReturnedPurchaseBillInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillInitializerService {
    @Autowired
    private HeaderRepository<OutboundHeader> repository;

    @Transactional(rollbackFor = Exception.class)
    public void initialize(long id) throws Exception {
        BillInitializer billInitializer;

        OutboundHeader header = repository.findOne(id);

        if (header.getSaleType() == SaleType.RETURNED_PURCHASE) {
            billInitializer = new ReturnedPurchaseBillInitializer(repository, header);
        } else {
            billInitializer = new OutboundSaleBillInitializer(repository, header);
        }

        billInitializer.initialize();
    }
}
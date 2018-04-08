package org.thinking.logistics.init.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.core.domain.BusinessAdapter;
import org.thinking.logistics.core.domain.support.SaleType;
import org.thinking.logistics.core.entity.bill.OutboundHeader;
import org.thinking.logistics.core.repository.OutboundHeaderRepository;
import org.thinking.logistics.init.domain.BillInitializer;
import org.thinking.logistics.init.domain.OutboundSaleBillInitializer;
import org.thinking.logistics.init.domain.ReturnedPurchaseBillInitializer;

@Service
public class BillInitializerService extends BusinessAdapter {
    @Autowired
    private OutboundHeaderRepository headerRepository;

    @Transactional(rollbackFor = Exception.class)
    public void initialize(long id) throws Exception {
        BillInitializer billInitializer;

        OutboundHeader header = this.headerRepository.findOne(id);

        if (header.getSaleType() == SaleType.RETURNED_PURCHASE) {
            billInitializer = new ReturnedPurchaseBillInitializer(this.getEmployee(header.getOwner(), "admin"), header);
        } else {
            billInitializer = new OutboundSaleBillInitializer(this.getEmployee(header.getOwner(), "admin"), header);
        }

        billInitializer.initialize();
    }
}
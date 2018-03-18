package org.jointown.logistics.init.domain;

import org.jointown.logistics.common.entity.BillHeader;
import org.jointown.logistics.common.entity.OutboundBillHeader;
import org.jointown.logistics.common.repository.OutboundBillHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBillInitializer implements BillInitializer {
    private OutboundBillHeader outboundBillHeader;

    @Autowired
    private OutboundBillHeaderRepository outboundBillHeaderRepository;

    public AbstractBillInitializer(long id) {
        this.outboundBillHeader = this.outboundBillHeaderRepository.findOne(id);
    }

    private void verify() throws Exception {
        if (this.outboundBillHeader.getStage().compareTo(BillHeader.Stage.INIT_FINISHED) >= 0) {
            return;
        }
    }
}
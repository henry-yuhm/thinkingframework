package org.jointown.logistics.init.domain;

import org.jointown.logistics.common.domain.BusinessAdapter;
import org.jointown.logistics.common.entity.BusinessBillHeader;
import org.jointown.logistics.common.entity.OutboundBillHeader;
import org.jointown.logistics.common.repository.OutboundBillHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBillInitializer extends BusinessAdapter implements BillInitializer {
    private OutboundBillHeader outboundBillHeader;

    @Autowired
    private OutboundBillHeaderRepository outboundBillHeaderRepository;

    public AbstractBillInitializer(long id) {
        this.outboundBillHeader = this.outboundBillHeaderRepository.findOne(id);
    }

    private void verify() throws Exception {
        if (this.outboundBillHeader.getStage().compareTo(BusinessBillHeader.Stage.INIT_COMPLETE) >= 0) {
            throw this.getException("单据已经初始化", this.outboundBillHeader, this.outboundBillHeader.getOwner());
        }

        if (this.outboundBillHeader.getCustomer() == null) {
            throw this.getException(this.getNullCustomerMessage(), this.outboundBillHeader, this.outboundBillHeader.getOwner());
        }

        if (this.outboundBillHeader.getBillDetails() == null || this.outboundBillHeader.getBillDetails().isEmpty()) {
            throw this.getException(this.getNullBillDetailMessage(), this.outboundBillHeader, this.outboundBillHeader.getOwner());
        }

        this.outboundBillHeader.getBillDetails().forEach(detail -> {

        });
    }
}
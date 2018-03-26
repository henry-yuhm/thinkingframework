package org.jointown.logistics.init.domain;

import org.jointown.logistics.common.domain.ErrorMessage;
import org.jointown.logistics.common.entity.bill.BillDetail;
import org.jointown.logistics.common.entity.bill.OutboundSaleBillDetail;
import org.jointown.logistics.common.entity.bill.OutboundSaleBillHeader;
import org.jointown.logistics.common.entity.support.OutboundBillStage;
import org.jointown.logistics.common.repository.OutboundBillHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public abstract class AbstractBillInitializer implements BillInitializer {
    private OutboundSaleBillHeader billHeader;

    @Autowired
    private OutboundBillHeaderRepository outboundBillHeaderRepository;

    public AbstractBillInitializer(long id) {
        this.billHeader = this.outboundBillHeaderRepository.findOne(id);
    }

    private void verify() throws Exception {
        StringBuilder message = new StringBuilder();

        if (this.billHeader.getBillStage().compareTo(OutboundBillStage.INIT_COMPLETE) >= 0) {
            throw ErrorMessage.getException("单据已经初始化", this.billHeader, this.billHeader.getOwner());
        }

        if (this.billHeader.getCustomer() == null) {
            throw ErrorMessage.getException(ErrorMessage.getNullCustomerMessage(), this.billHeader, this.billHeader.getOwner());
        }

        if (this.billHeader.getBillDetails() == null || this.billHeader.getBillDetails().isEmpty()) {
            throw ErrorMessage.getException(ErrorMessage.getNullBillDetailMessage(), this.billHeader, this.billHeader.getOwner());
        }

        for (BillDetail billDetail : this.billHeader.getBillDetails()) {
            OutboundSaleBillDetail detail = (OutboundSaleBillDetail) billDetail;

            if (detail.getGoods() == null) {
                throw ErrorMessage.getException(ErrorMessage.getNullGoodsMessage(), this.billHeader, this.billHeader.getOwner());
            } else {
                if (detail.getActualQuantity().getQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    detail.setWholeQuantity(detail.getActualQuantity().getQuantity().subtract(detail.getActualQuantity().getRemainder()));
                    detail.setRemainderQuantity(detail.getActualQuantity().getRemainder());
                } else {
                    message.append("商品【" + detail.getGoods().getNo() + "】【" + detail.getGoods().getName() + "】数量为0");
                }
            }
        }

        if (message.length() > 0) {
            message.insert(0, "明细校验错误");
            throw ErrorMessage.getException(message.toString(), this.billHeader, this.billHeader.getOwner());
        }
    }
}
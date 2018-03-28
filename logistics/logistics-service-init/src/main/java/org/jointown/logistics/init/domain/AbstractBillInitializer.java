package org.jointown.logistics.init.domain;

import org.jointown.logistics.core.domain.ErrorMessage;
import org.jointown.logistics.core.entity.bill.BillDetail;
import org.jointown.logistics.core.entity.bill.OutboundBillHeader;
import org.jointown.logistics.core.entity.bill.OutboundSaleBillDetail;
import org.jointown.logistics.core.entity.support.OutboundBillStage;
import org.jointown.logistics.core.repository.BillHeaderRepository;

import java.math.BigDecimal;

public abstract class AbstractBillInitializer<T extends OutboundBillHeader> implements BillInitializer<OutboundBillHeader> {
    private BillHeaderRepository<? extends OutboundBillHeader> repository;

    private T header;

    public AbstractBillInitializer(BillHeaderRepository<? extends OutboundBillHeader> repository, T header) {
        this.repository = repository;
        this.header = header;
    }

    @Override
    public BillHeaderRepository getRepository() {
        return this.repository;
    }

    @Override
    public T getHeader() {
        return this.header;
    }

    @Override
    public void verify() throws Exception {
        StringBuilder message = new StringBuilder();

        if (this.header.getStage().compareTo(OutboundBillStage.INIT_COMPLETE) >= 0) {
            throw ErrorMessage.getException("单据已经初始化", this.header, this.header.getOwner());
        }

        if (this.header.getCustomer() == null) {
            throw ErrorMessage.getException(ErrorMessage.getNullCustomerMessage(), this.header, this.header.getOwner());
        }

        if (this.header.getDetails() == null || this.header.getDetails().isEmpty()) {
            throw ErrorMessage.getException(ErrorMessage.getNullBillDetailMessage(), this.header, this.header.getOwner());
        }

        for (BillDetail billDetail : this.header.getDetails()) {
            OutboundSaleBillDetail detail = (OutboundSaleBillDetail) billDetail;

            if (detail.getGoods() == null) {
                throw ErrorMessage.getException(ErrorMessage.getNullGoodsMessage(), this.header, this.header.getOwner());
            } else {
                if (detail.getFactQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    detail.setWholeQuantity(detail.getFactQuantity().subtract(detail.getFactRemainder()));
                    detail.setRemainderQuantity(detail.getFactRemainder());
                } else {
                    message.append("商品【").append(detail.getGoods().getNo()).append("】【").append(detail.getGoods().getName()).append("】数量为0");
                }
            }
        }

        if (message.length() > 0) {
            message.insert(0, "明细校验错误");
            throw ErrorMessage.getException(message.toString(), this.header, this.header.getOwner());
        }
    }

    @Override
    public void initialize() throws Exception {
        this.verify();

        this.header.setStage(OutboundBillStage.INIT_COMPLETE);
    }
}
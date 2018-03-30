package org.jointown.logistics.init.domain;

import org.jointown.logistics.core.domain.ErrorMessage;
import org.jointown.logistics.core.entity.bill.Detail;
import org.jointown.logistics.core.entity.bill.OutboundHeader;
import org.jointown.logistics.core.entity.bill.SaleOutboundDetail;
import org.jointown.logistics.core.entity.support.OutboundStage;
import org.jointown.logistics.core.repository.HeaderRepository;

import java.math.BigDecimal;

public abstract class AbstractBillInitializer<T extends OutboundHeader> implements BillInitializer<OutboundHeader> {
    private HeaderRepository<? extends OutboundHeader> repository;

    private T header;

    public AbstractBillInitializer(HeaderRepository<? extends OutboundHeader> repository, T header) {
        this.repository = repository;
        this.header = header;
    }

    @Override
    public HeaderRepository getRepository() {
        return this.repository;
    }

    @Override
    public T getHeader() {
        return this.header;
    }

    @Override
    public void verify() throws Exception {
        StringBuilder message = new StringBuilder();

        if (this.header.getStage().compareTo(OutboundStage.INIT_COMPLETE) >= 0) {
            throw ErrorMessage.getException("单据已经初始化", this.header, this.header.getOwner());
        }

        if (this.header.getCustomer() == null) {
            throw ErrorMessage.getException(ErrorMessage.getNullCustomerMessage(), this.header, this.header.getOwner());
        }

        if (this.header.getDetails() == null || this.header.getDetails().isEmpty()) {
            throw ErrorMessage.getException(ErrorMessage.getNullDetailMessage(), this.header, this.header.getOwner());
        }

        for (Detail billDetail : this.header.getDetails()) {
            SaleOutboundDetail detail = (SaleOutboundDetail) billDetail;

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

        this.header.setStage(OutboundStage.INIT_COMPLETE);
    }
}
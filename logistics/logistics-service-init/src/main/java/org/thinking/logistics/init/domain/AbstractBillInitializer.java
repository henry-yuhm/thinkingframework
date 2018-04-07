package org.thinking.logistics.init.domain;

import org.thinking.logistics.core.domain.ErrorMessage;
import org.thinking.logistics.core.entity.bill.OutboundDetail;
import org.thinking.logistics.core.entity.bill.OutboundHeader;
import org.thinking.logistics.core.entity.support.OutboundStage;
import org.thinking.logistics.core.repository.HeaderRepository;

import java.math.BigDecimal;

public abstract class AbstractBillInitializer implements BillInitializer {
    protected OutboundHeader header;

    private HeaderRepository<OutboundHeader> repository;

    public AbstractBillInitializer(HeaderRepository<OutboundHeader> repository, OutboundHeader header) {
        this.repository = repository;
        this.header = header;
    }

    @Override
    public void verify() throws Exception {
        StringBuilder message = new StringBuilder();

        if (this.header.getStage().compareTo(OutboundStage.INITIALIZED) >= 0) {
            throw ErrorMessage.getException("单据已经初始化", this.header, this.header.getOwner());
        }

        if (this.header.getCustomer() == null) {
            throw ErrorMessage.getException(ErrorMessage.getNullCustomerMessage(), this.header, this.header.getOwner());
        }

        if (this.header.getDetails() == null || this.header.getDetails().isEmpty()) {
            throw ErrorMessage.getException(ErrorMessage.getNullDetailMessage(), this.header, this.header.getOwner());
        }

        for (OutboundDetail detail : this.header.getDetails()) {
            if (detail.getGoods() == null) {
                throw ErrorMessage.getException(ErrorMessage.getNullGoodsMessage(), this.header, this.header.getOwner());
            } else {
                if (detail.getFactQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    detail.setWholeQuantity(detail.getFactQuantity().subtract(detail.getFactRemainder()));
                    detail.setRemainderQuantity(detail.getFactRemainder());
                } else {
                    message.append("商品【").append(detail.getGoods().getNumber()).append("】【").append(detail.getGoods().getName()).append("】数量为0");
                }
            }
        }

        if (message.length() > 0) {
            message.insert(0, "明细校验错误");
            throw ErrorMessage.getException(message.toString(), this.header, this.header.getOwner());
        }
    }

    @Override
    public void save() {
        this.repository.save(this.header);
    }

    @Override
    public void initialize() throws Exception {
        this.verify();

        this.header.setStage(OutboundStage.INITIALIZED);
    }
}
package org.thinking.logistics.order.initialization.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessAdapter;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.support.OutboundStage;
import org.thinking.logistics.services.core.entity.bill.OutboundDetail;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.repository.OutboundHeaderRepository;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractInitializer extends BusinessAdapter implements Initializer {
    OutboundHeader header;

    @Resource
    private OutboundHeaderRepository headerRepository;

    public AbstractInitializer(OutboundHeader header) {
        this.header = header;
    }

    @Override
    public void verify() throws Exception {
        StringBuilder message = new StringBuilder();

        if (this.header.getStage().compareTo(OutboundStage.INITIALIZED) >= 0) {
            throw CompositeException.getException("单据已经初始化", this.header, this.header.getOwner());
        }

        if (this.header.getCustomer() == null) {
            throw CompositeException.getException(CompositeException.getNullCustomerMessage(), this.header, this.header.getOwner());
        }

        if (this.header.getDetails() == null || this.header.getDetails().isEmpty()) {
            throw CompositeException.getException(CompositeException.getNullDetailMessage(), this.header, this.header.getOwner());
        }

        for (OutboundDetail detail : this.header.getDetails()) {
            if (detail.getGoods() == null) {
                throw CompositeException.getException(CompositeException.getNullGoodsMessage(), this.header, this.header.getOwner());
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
            throw CompositeException.getException(message.toString(), this.header, this.header.getOwner());
        }
    }

    @Override
    public void save() {
        this.headerRepository.save(this.header);
    }

    @Override
    public void initialize() throws Exception {
        this.verify();

        this.header.setStage(OutboundStage.INITIALIZED);
    }
}
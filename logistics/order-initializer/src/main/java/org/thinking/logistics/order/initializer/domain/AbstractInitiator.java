package org.thinking.logistics.order.initializer.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessBase;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.support.NotExistsEntityException;
import org.thinking.logistics.services.core.domain.support.OutboundStage;
import org.thinking.logistics.services.core.service.document.ShipmentOrderService;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractInitiator extends BusinessBase implements Initiator {
    private ShipmentOrderHeader header;

    @Resource
    private ShipmentOrderService orderService;

    public AbstractInitiator(ShipmentOrderHeader header) {
        this.header = header;
    }

    @Override
    public void verify() throws Exception {
        StringBuilder message = new StringBuilder();

        if (this.header.getStage().compareTo(OutboundStage.INITIALIZED) >= 0) {
            throw CompositeException.getException("单据已经初始化", this.header, this.header.getOwner());
        }

        if (this.header.getCustomer() == null) {
            throw CompositeException.getException(NotExistsEntityException.CUSTOMER.name(), this.header, this.header.getOwner());
        }

        if (this.header.getDetails() == null || this.header.getDetails().isEmpty()) {
            throw CompositeException.getException(NotExistsEntityException.DETAIL.name(), this.header, this.header.getOwner());
        }

        for (ShipmentOrderDetail detail : this.header.getDetails()) {
            if (detail.getItem() == null) {
                throw CompositeException.getException(NotExistsEntityException.ITEM.name(), this.header, this.header.getOwner());
            } else {
                if (detail.getFactQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    detail.setWholepiecesQuantity(detail.getFactQuantity().subtract(detail.getFactRemainder()));
                    detail.setRemainderQuantity(detail.getFactRemainder());
                } else {
                    message.append("商品【").append(detail.getItem().getNo()).append("】【").append(detail.getItem().getName()).append("】数量为0");
                }
            }
        }

        if (message.length() > 0) {
            message.insert(0, "明细校验错误");
            throw CompositeException.getException(message.toString(), this.header, this.header.getOwner());
        }
    }

    @Override
    public void save() throws Exception {
        this.header.setStage(OutboundStage.INITIALIZED);
        this.orderService.getRepository().save(this.header);
    }

    @Override
    public void init() throws Exception {
        this.verify();

        this.save();
    }
}
package org.thinking.sce.order.initializer.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.BusinessBase;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.document.ShipmentOrderDetail;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.support.NotExistsEntityException;
import org.thinking.sce.service.core.domain.support.ShipmentStatus;
import org.thinking.sce.service.core.service.document.ShipmentOrderService;

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

        if (this.header.getShipmentStatus().compareTo(ShipmentStatus.INITIALIZED) >= 0) {
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
                if (detail.getActualQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    detail.setCasesQuantity(detail.getActualQuantity().subtract(detail.getActualRemainder()));
                    detail.setRemainderQuantity(detail.getActualRemainder());
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
        this.header.setShipmentStatus(ShipmentStatus.INITIALIZED);
        this.orderService.getRepository().save(this.header);
    }

    @Override
    public void init() throws Exception {
        this.verify();

        this.save();
    }
}
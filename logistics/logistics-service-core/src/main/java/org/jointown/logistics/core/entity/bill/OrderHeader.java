package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.support.OrderStage;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class OrderHeader extends Header {
    @Column(nullable = false)
    private OrderStage stage;//订单阶段
}
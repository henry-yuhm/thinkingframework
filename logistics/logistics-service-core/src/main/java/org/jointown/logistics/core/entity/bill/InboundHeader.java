package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.support.InboundKind;
import org.jointown.logistics.core.entity.support.InboundStage;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class InboundHeader extends Header {
    @Column(nullable = false)
    private InboundStage stage;//阶段

    @Column(nullable = false)
    private InboundKind kind;//入库类型

    @Column(nullable = false)
    private boolean passback;//是否回传

    @Column(nullable = false)
    private boolean chargeup;//是否记账

    @Column(nullable = false)
    private boolean executed;//是否执行

}
package org.jointown.logistics.core.entity.bill;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class InboundBillHeader extends BillHeader {
    @Column(nullable = false)
    private boolean passback;//是否回传
}
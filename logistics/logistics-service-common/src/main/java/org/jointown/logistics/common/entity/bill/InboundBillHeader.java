package org.jointown.logistics.common.entity.bill;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class InboundBillHeader extends BillHeader {
    private boolean passback;//是否回传
}
package org.jointown.logistics.core.entity.bill;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class DistributionHeader extends Header {
    @OneToOne(fetch = FetchType.LAZY)
    private OutboundHeader header;//出库单抬头
}
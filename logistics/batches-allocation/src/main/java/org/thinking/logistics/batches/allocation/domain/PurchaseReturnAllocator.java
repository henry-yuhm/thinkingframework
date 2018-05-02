package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.bill.OutboundDetail;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnAllocator extends AbstractAllocator {
    public PurchaseReturnAllocator(OutboundHeader header) {
        super(header);
    }

    @Override
    public void initialize(OutboundDetail detail) throws Exception {
        super.initialize(detail);
    }

    @Override
    public void setDetail(OutboundDetail detail) throws Exception {
        super.setDetail(detail);
    }

    @Override
    public void allocate() throws Exception {

    }
}
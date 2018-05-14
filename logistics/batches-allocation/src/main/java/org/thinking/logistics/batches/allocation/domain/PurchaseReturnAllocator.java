package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnAllocator extends AbstractAllocator {
    public PurchaseReturnAllocator(OutboundOrderHeader header) throws Exception {
        super(header);
    }

    @Override
    public void initialize(OutboundOrderDetail detail) throws Exception {
        super.initialize(detail);
    }

    @Override
    public void setDetail(OutboundOrderDetail detail) throws Exception {
        super.setDetail(detail);
    }

    @Override
    public void allocate() throws Exception {

    }
}
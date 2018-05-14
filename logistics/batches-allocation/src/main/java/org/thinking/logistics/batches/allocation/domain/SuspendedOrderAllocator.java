package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuspendedOrderAllocator extends AbstractAllocator {
    public SuspendedOrderAllocator(OutboundOrderHeader header) throws Exception {
        super(header);
    }

    @Override
    public void setDetail(OutboundOrderDetail detail) throws Exception {
        super.setDetail(detail);
    }

    @Override
    public void allocate() throws Exception {

    }
}
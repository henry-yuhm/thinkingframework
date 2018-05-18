package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuspendedOrderAllocator extends AbstractAllocator {
    public SuspendedOrderAllocator(OutboundOrderHeader header) throws Exception {
        super(header);
    }

    @Override
    public void allocate() throws Exception {

    }
}
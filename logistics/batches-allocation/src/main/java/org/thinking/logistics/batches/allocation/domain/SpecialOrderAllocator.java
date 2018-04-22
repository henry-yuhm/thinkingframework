package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.entity.bill.OutboundDetail;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecialOrderAllocator extends AbstractAllocator {
    public SpecialOrderAllocator(OutboundHeader header) {
        super(header);
    }

    @Override
    public void acquireLocation(OutboundDetail detail) throws Exception {
        super.acquireLocation(detail);
    }

    @Override
    public void setDetail(OutboundDetail detail) throws Exception {
        super.setDetail(detail);
    }

    @Override
    public void save() {
        super.save();
    }

    @Override
    public void allocate() throws Exception {

    }
}
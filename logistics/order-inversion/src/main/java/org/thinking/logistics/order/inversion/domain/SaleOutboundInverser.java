package org.thinking.logistics.order.inversion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOutboundInverser extends AbstractInverser {
    @Override
    public void inverse(OutboundOrderDetail detail) {

    }
}
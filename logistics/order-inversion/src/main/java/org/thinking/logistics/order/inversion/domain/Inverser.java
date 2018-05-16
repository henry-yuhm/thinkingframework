package org.thinking.logistics.order.inversion.domain;

import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;

public interface Inverser {
    void inverse(OutboundOrderDetail detail);

    void audit();
}
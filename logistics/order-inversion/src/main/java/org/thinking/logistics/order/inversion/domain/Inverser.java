package org.thinking.logistics.order.inversion.domain;

import org.thinking.logistics.services.core.domain.documents.InverseOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;

import java.util.List;

public interface Inverser {
    void inverse();

    void inverse(OutboundOrderDetail detail);

    void calculateQuantity(InverseOrderDetail inverseOrderDetail, OutboundOrderDetail detail);

    void audit(List<InverseOrderDetail> details) throws Exception;
}
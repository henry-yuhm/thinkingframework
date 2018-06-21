package org.thinking.logistics.order.inversion.domain;

import org.thinking.logistics.services.core.domain.document.InverseOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;

import java.util.List;

public interface Inverser {
    void inverse();

    void inverse(ShipmentOrderDetail detail);

    void calculateQuantity(InverseOrderDetail inverseOrderDetail, ShipmentOrderDetail detail);

    void audit(List<InverseOrderDetail> details) throws Exception;
}
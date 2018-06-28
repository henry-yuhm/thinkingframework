package org.thinking.logistics.order.inversion.domain;

import org.thinking.logistics.services.core.domain.document.ReversionNoteDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;

import java.util.List;

public interface Reversor {
    void revert();

    void revert(ShipmentOrderDetail detail);

    void calculateQuantity(ShipmentOrderDetail detail, ReversionNoteDetail reversionNoteDetail);

    void audit(List<ReversionNoteDetail> details) throws Exception;
}
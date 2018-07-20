package org.thinking.sce.order.reversor.domain;

import org.thinking.sce.service.core.domain.document.*;

import java.util.List;

public interface Reversor {
    void revert();

    void revert(ShipmentOrderDetail detail);

    void calculateQuantity(ShipmentOrderDetail detail, ReversionNoteDetail reversionNoteDetail);

    void audit(List<ReversionNoteDetail> details) throws Exception;
}
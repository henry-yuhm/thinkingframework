package org.thinking.logistics.services.core.service.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.common.Owner;
import org.thinking.logistics.services.core.domain.common.Warehouse;
import org.thinking.logistics.services.core.domain.document.QReversionNoteDetail;
import org.thinking.logistics.services.core.domain.document.QShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.QShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentOrderService extends DomainService<QShipmentOrderHeader, ShipmentOrderHeader, Long> {
    private final ReversionNoteService reversionNoteService;

    @Autowired
    public ShipmentOrderService(EntityManager entityManager, DomainRepository<ShipmentOrderHeader, Long> repository, ReversionNoteService reversionNoteService) {
        super(entityManager, repository, QShipmentOrderHeader.shipmentOrderHeader);
        this.reversionNoteService = reversionNoteService;
    }

    public final ShipmentOrderHeader acquire(long id, boolean verifiable) throws Exception {
        ShipmentOrderHeader header = this.getFactory().selectFrom(this.getPath())
            .where(this.getPath().id.eq(id))
            .fetchOne();

        if (verifiable) {
            if (header == null) {
                throw CompositeException.getException("无此ID【" + id + "】的出库订单");
            }
        }

        return header;
    }

    public final ShipmentOrderHeader acquire(Owner owner, String no, boolean verifiable) throws Exception {
        ShipmentOrderHeader header = this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().owner.eq(owner),
                this.getPath().no.eq(no)
            )
            .fetchOne();

        if (verifiable) {
            if (header == null) {
                throw CompositeException.getException("无此编号【" + no + "】的出库订单", owner);
            }
        }

        return header;
    }

    public final List<ShipmentOrderHeader> acquire(Warehouse warehouse, String wave, boolean verifiable) throws Exception {
        List<ShipmentOrderHeader> headers = this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().wave.eq(wave)
            )
            .fetch();

        if (verifiable) {
            if (headers == null || headers.size() == 0) {
                throw CompositeException.getException("无此波次【" + wave + "】的出库订单", warehouse);
            }
        }

        return headers;
    }

    public final boolean isReversed(ShipmentOrderHeader header) {
        QShipmentOrderDetail detail = QShipmentOrderDetail.shipmentOrderDetail;
        BigDecimal orderQuantity = Optional.ofNullable(this.getFactory().selectFrom(this.getPath())
            .innerJoin(this.getPath().details, detail)
            .where(
                this.getPath().eq(header),
                detail.original.isTrue()
            )
            .select(detail.expectedQuantity.sum())
            .fetchOne()
        ).orElse(BigDecimal.ZERO);

        QReversionNoteDetail reversionNoteDetail = QReversionNoteDetail.reversionNoteDetail;
        BigDecimal reverseQuantity = Optional.ofNullable(this.reversionNoteService.getFactory().selectFrom(reversionNoteDetail)
            .where(reversionNoteDetail.header.eq(header))
            .select(reversionNoteDetail.quantity.sum())
            .fetchOne()
        ).orElse(BigDecimal.ZERO);

        return orderQuantity.compareTo(reverseQuantity) == 0;
    }
}
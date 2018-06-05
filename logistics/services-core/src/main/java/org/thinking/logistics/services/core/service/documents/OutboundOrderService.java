package org.thinking.logistics.services.core.service.documents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.core.Owner;
import org.thinking.logistics.services.core.domain.core.Warehouse;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.documents.QInverseOrderDetail;
import org.thinking.logistics.services.core.domain.documents.QOutboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.QOutboundOrderHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OutboundOrderService extends DomainService<QOutboundOrderHeader, OutboundOrderHeader, Long> {
    private final InverseOrderService inverseOrderService;

    @Autowired
    public OutboundOrderService(EntityManager entityManager, DomainRepository<OutboundOrderHeader, Long> repository, InverseOrderService inverseOrderService) {
        super(entityManager, repository, QOutboundOrderHeader.outboundOrderHeader);
        this.inverseOrderService = inverseOrderService;
    }

    public final OutboundOrderHeader acquire(long id, boolean verifiable) throws Exception {
        OutboundOrderHeader header = this.getFactory().selectFrom(this.getPath())
            .where(this.getPath().id.eq(id))
            .fetchOne();

        if (verifiable) {
            if (header == null) {
                throw CompositeException.getException("无此ID【" + id + "】的出库订单");
            }
        }

        return header;
    }

    public final OutboundOrderHeader acquire(Owner owner, String no, boolean verifiable) throws Exception {
        OutboundOrderHeader header = this.getFactory().selectFrom(this.getPath())
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

    public final List<OutboundOrderHeader> acquire(Warehouse warehouse, String wave, boolean verifiable) throws Exception {
        List<OutboundOrderHeader> headers = this.getFactory().selectFrom(this.getPath())
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

    public final boolean isInversed(OutboundOrderHeader header) {
        QOutboundOrderDetail detail = QOutboundOrderDetail.outboundOrderDetail;
        BigDecimal orderQuantity = Optional.ofNullable(this.getFactory().selectFrom(this.getPath())
            .innerJoin(this.getPath().details, detail)
            .where(
                this.getPath().eq(header),
                detail.original.isTrue()
            )
            .select(detail.planQuantity.sum())
            .fetchOne()
        ).orElse(BigDecimal.ZERO);

        QInverseOrderDetail inverseDetail = QInverseOrderDetail.inverseOrderDetail;
        BigDecimal inverseQuantity = Optional.ofNullable(this.inverseOrderService.getFactory().selectFrom(inverseDetail)
            .where(inverseDetail.header.eq(header))
            .select(inverseDetail.quantity.sum())
            .fetchOne()
        ).orElse(BigDecimal.ZERO);

        return orderQuantity.compareTo(inverseQuantity) == 0;
    }
}
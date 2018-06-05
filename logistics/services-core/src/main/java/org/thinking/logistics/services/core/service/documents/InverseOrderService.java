package org.thinking.logistics.services.core.service.documents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.core.Warehouse;
import org.thinking.logistics.services.core.domain.documents.InverseOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.QInverseOrderDetail;
import org.thinking.logistics.services.core.domain.support.InverseStage;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class InverseOrderService extends DomainService<QInverseOrderDetail, InverseOrderDetail, Long> {
    @Autowired
    public InverseOrderService(EntityManager entityManager, DomainRepository<InverseOrderDetail, Long> repository) {
        super(entityManager, repository, QInverseOrderDetail.inverseOrderDetail);
    }

    public final InverseOrderDetail acquire(Warehouse warehouse, OutboundOrderDetail detail, InverseStage stage) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().detail.eq(detail),
                this.getPath().stage.eq(stage)
            )
            .fetchOne();
    }
}
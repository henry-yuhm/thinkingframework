package org.thinking.logistics.services.core.service.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.common.Warehouse;
import org.thinking.logistics.services.core.domain.document.QReversionNoteDetail;
import org.thinking.logistics.services.core.domain.document.ReversionNoteDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.support.ReversionStage;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class ReversionNoteService extends DomainService<QReversionNoteDetail, ReversionNoteDetail, Long> {
    @Autowired
    public ReversionNoteService(EntityManager entityManager, DomainRepository<ReversionNoteDetail, Long> repository) {
        super(entityManager, repository, QReversionNoteDetail.reversionNoteDetail);
    }

    public final ReversionNoteDetail acquire(Warehouse warehouse, ShipmentOrderDetail detail, ReversionStage reversionStage) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().detail.eq(detail),
                this.getPath().reversionStage.eq(reversionStage)
            )
            .fetchOne();
    }
}
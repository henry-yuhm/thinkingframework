package org.thinking.logistics.services.core.service.stagingarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.stagingarea.QStagingarea;
import org.thinking.logistics.services.core.domain.stagingarea.Stagingarea;
import org.thinking.logistics.services.core.domain.support.OutboundStage;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@Service
public class StagingareaService extends DomainService<QStagingarea, Stagingarea, Long> {
    @Autowired
    public StagingareaService(EntityManager entityManager, DomainRepository<Stagingarea, Long> repository) {
        super(entityManager, repository, QStagingarea.stagingarea);
    }

    public final List<String> acquireAvailableArea(Stagingarea stagingarea) {
        return this.getFactory().selectFrom(this.getPath())
            .select(this.getPath().no)
            .where(
                this.getPath().type.eq(stagingarea.getType()),
                this.getPath().category.eq(stagingarea.getCategory()),
                this.getPath().locking.isFalse(),
                this.getPath().available.isTrue(),
                this.getPath().billType.eq(stagingarea.getBillType()),
                this.getPath().pickupMode.eq(stagingarea.getPickupMode()),
                this.getPath().owners.contains(stagingarea.getOwners().stream().findAny().get()),
                this.getPath().direction.eq(stagingarea.getDirection()))
            .orderBy(this.getPath().no.asc())
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch();
    }

    public final List<Stagingarea> acquireAvailableArea(String from, String to, Stagingarea stagingarea) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().no.goe(from),
                this.getPath().no.loe(to),
                this.getPath().type.eq(stagingarea.getType()),
                this.getPath().category.eq(stagingarea.getCategory()),
                this.getPath().locking.isFalse(),
                this.getPath().available.isTrue(),
                this.getPath().billType.eq(stagingarea.getBillType()),
                this.getPath().pickupMode.eq(stagingarea.getPickupMode()),
                this.getPath().owners.contains(stagingarea.getOwners().stream().findAny().get()),
                this.getPath().direction.eq(stagingarea.getDirection()))
            .orderBy(this.getPath().no.asc())
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch();
    }

    @Transactional(rollbackFor = Exception.class)
    public void cleanup(final ShipmentOrderHeader header) throws Exception {
        if (header.getStage().compareTo(OutboundStage.RECHECK_COMPLETE) < 0 && !header.isInversed()) {
            throw CompositeException.getException("单据任务未完成，不能清空月台", header, header.getOwner());
        }

        List<Stagingarea> stagingareas = this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(header.getWarehouse()),
                this.getPath().no.goe(header.getSourceStagingarea().getNo()),
                this.getPath().no.loe(header.getTargetStagingarea().getNo())
            )
            .fetch();

        stagingareas.forEach(stagingarea -> stagingarea.setAvailable(true));

        this.getRepository().saveAll(stagingareas);
    }
}
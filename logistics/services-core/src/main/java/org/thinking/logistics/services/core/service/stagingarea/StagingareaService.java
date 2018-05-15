package org.thinking.logistics.services.core.service.stagingarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.stagingarea.QStagingarea;
import org.thinking.logistics.services.core.domain.stagingarea.Stagingarea;
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
                this.getPath().takegoodsMode.eq(stagingarea.getTakegoodsMode()),
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
                this.getPath().takegoodsMode.eq(stagingarea.getTakegoodsMode()),
                this.getPath().owners.contains(stagingarea.getOwners().stream().findAny().get()),
                this.getPath().direction.eq(stagingarea.getDirection()))
            .orderBy(this.getPath().no.asc())
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch();
    }
}
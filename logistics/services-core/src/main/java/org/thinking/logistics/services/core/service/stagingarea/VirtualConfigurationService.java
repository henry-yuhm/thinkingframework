package org.thinking.logistics.services.core.service.stagingarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.Direction;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;
import org.thinking.logistics.services.core.domain.stagingarea.QVirtualConfiguration;
import org.thinking.logistics.services.core.domain.stagingarea.VirtualConfiguration;
import org.thinking.logistics.services.core.domain.support.StagingareaCategory;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class VirtualConfigurationService extends DomainService<QVirtualConfiguration, VirtualConfiguration, Long> {
    @Autowired
    public VirtualConfigurationService(EntityManager entityManager, DomainRepository<VirtualConfiguration, Long> repository) {
        super(entityManager, repository, QVirtualConfiguration.virtualConfiguration);
    }

    public final VirtualConfiguration acquire(OutboundHeader header, StagingareaCategory stagingareaCategory, Direction direction) throws Exception {
        VirtualConfiguration configuration = this.getJpaQueryFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(header.getWarehouse()),
                this.getPath().owner.isNull().or(this.getPath().owner.eq(header.getOwner())),
                this.getPath().available.eq(true),
                this.getPath().billCategory.isNull().or(this.getPath().billCategory.eq(header.getCategory())),
                this.getPath().takegoodsMode.isNull().or(this.getPath().takegoodsMode.eq(header.getTakegoodsMode())),
                this.getPath().saleType.isNull().or(this.getPath().saleType.eq(header.getSaleType())),
                this.getPath().stagingareaCategory.isNull().or(this.getPath().stagingareaCategory.eq(stagingareaCategory)),
                this.getPath().direction.isNull().or(this.getPath().direction.eq(direction)))
            .orderBy(
                this.getPath().owner.when(header.getOwner()).then(0).otherwise(1).asc(),
                this.getPath().billCategory.desc(),
                this.getPath().takegoodsMode.desc(),
                this.getPath().direction.no.desc())
            .fetchFirst();

        return configuration;
    }
}
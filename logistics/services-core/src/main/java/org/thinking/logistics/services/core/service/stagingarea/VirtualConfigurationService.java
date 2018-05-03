package org.thinking.logistics.services.core.service.stagingarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.Direction;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;
import org.thinking.logistics.services.core.domain.stagingarea.VirtualConfiguration;
import org.thinking.logistics.services.core.domain.stagingarea.dsl.QVirtualConfiguration;
import org.thinking.logistics.services.core.domain.support.StagingareaCategory;
import org.thinking.logistics.services.core.service.EntityService;

@Service
public class VirtualConfigurationService extends EntityService<QVirtualConfiguration, VirtualConfiguration, Long> {
    @Autowired
    public VirtualConfigurationService(JpaRepository<VirtualConfiguration, Long> repository) {
        super(repository);
        this.setProbe(QVirtualConfiguration.virtualConfiguration);
    }

    public final VirtualConfiguration findOne(OutboundHeader header, StagingareaCategory stagingareaCategory, Direction direction) throws Exception {
        return this.getQueryFactory().selectFrom(this.getProbe())
            .where(
                this.getProbe().warehouse.eq(header.getWarehouse()),
                this.getProbe().owner.isNull().or(this.getProbe().owner.eq(header.getOwner())),
                this.getProbe().available.eq(true),
                this.getProbe().billCategory.isNull().or(this.getProbe().billCategory.eq(header.getCategory())),
                this.getProbe().takegoodsMode.isNull().or(this.getProbe().takegoodsMode.eq(header.getTakegoodsMode())),
                this.getProbe().saleType.isNull().or(this.getProbe().saleType.eq(header.getSaleType())),
                this.getProbe().stagingareaCategory.isNull().or(this.getProbe().stagingareaCategory.eq(stagingareaCategory)),
                this.getProbe().direction.isNull().or(this.getProbe().direction.eq(direction)))
            .orderBy(this.getProbe().owner.when(header.getOwner()).then(0).otherwise(1).asc())
            .orderBy(this.getProbe().billCategory.desc())
            .orderBy(this.getProbe().takegoodsMode.desc())
            .orderBy(this.getProbe().direction.no.desc())
            .fetchFirst();
    }
}
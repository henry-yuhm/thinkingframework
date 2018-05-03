package org.thinking.logistics.services.core.service.stagingarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;
import org.thinking.logistics.services.core.domain.stagingarea.PhysicalConfiguration;
import org.thinking.logistics.services.core.domain.stagingarea.dsl.QPhysicalConfiguration;
import org.thinking.logistics.services.core.service.EntityService;

@Service
public class PhysicalConfigurationService extends EntityService<QPhysicalConfiguration, PhysicalConfiguration, Long> {
    @Autowired
    public PhysicalConfigurationService(JpaRepository<PhysicalConfiguration, Long> repository) {
        super(repository);
        this.setProbe(QPhysicalConfiguration.physicalConfiguration);
    }

    public final PhysicalConfiguration findOne(OutboundHeader header) throws Exception {
        PhysicalConfiguration configuration = this.getQueryFactory().selectFrom(this.getProbe())
            .where(
                this.getProbe().warehouse.eq(header.getWarehouse()),
                this.getProbe().owner.eq(header.getOwner()),
                this.getProbe().billCategory.eq(header.getCategory()))
            .fetchOne();

        if (configuration == null) {
            throw CompositeException.getException("物理月台配置资料未设置单据对应的业主与类别", header, header.getOwner());
        }

        return configuration;
    }
}
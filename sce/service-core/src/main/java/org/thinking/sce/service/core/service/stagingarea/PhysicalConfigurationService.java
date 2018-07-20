package org.thinking.sce.service.core.service.stagingarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.stagingarea.PhysicalConfiguration;
import org.thinking.sce.service.core.repository.DomainRepository;
import org.thinking.sce.service.core.service.DomainService;

import javax.persistence.EntityManager;

@Service
public class PhysicalConfigurationService extends DomainService<QPhysicalConfiguration, PhysicalConfiguration, Long> {
    @Autowired
    public PhysicalConfigurationService(EntityManager entityManager, DomainRepository<PhysicalConfiguration, Long> repository) {
        super(entityManager, repository, QPhysicalConfiguration.physicalConfiguration);
    }

    public final PhysicalConfiguration acquire(ShipmentOrderHeader header, boolean verifiable) throws Exception {
        PhysicalConfiguration configuration = this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(header.getWarehouse()),
                this.getPath().owner.eq(header.getOwner()),
                this.getPath().itemClass.eq(header.getItemClass()))
            .fetchOne();

        if (verifiable) {
            if (configuration == null) {
                throw CompositeException.getException("物理月台配置资料未设置单据对应的业主与类别", header, header.getOwner());
            }
        }

        return configuration;
    }
}
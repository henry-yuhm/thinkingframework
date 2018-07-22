package org.thinking.sce.service.core.service.stagingarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.stagingarea.QStagingareaConfiguration;
import org.thinking.sce.service.core.domain.stagingarea.StagingareaConfiguration;
import org.thinking.sce.service.core.repository.DomainRepository;
import org.thinking.sce.service.core.service.DomainService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@Service
public class StagingareaConfigurationService extends DomainService<QStagingareaConfiguration, StagingareaConfiguration, Long> {
    @Autowired
    public StagingareaConfigurationService(EntityManager entityManager, DomainRepository<StagingareaConfiguration, Long> repository) {
        super(entityManager, repository, QStagingareaConfiguration.stagingareaConfiguration);
    }

    public final StagingareaConfiguration acquire(ShipmentOrderHeader header, boolean verifiable) throws Exception {
        StagingareaConfiguration configuration = this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(header.getWarehouse()),
                this.getPath().owner.eq(header.getOwner()),
                this.getPath().pickupMode.eq(header.getPickupMode()))
            .fetchOne();

        if (verifiable) {
            if (configuration == null) {
                throw CompositeException.getException("月台配置参数未设定", header, header.getOwner());
            }

            if (configuration.getSmallQuantity().compareTo(BigDecimal.ZERO) == 0 || configuration.getLargeQuantity().compareTo(BigDecimal.ZERO) == 0) {
                throw CompositeException.getException("提货方式【" + header.getPickupMode().toString() + "】对应的月台件数未设定", header, header.getOwner());
            }
        }

        return configuration;
    }
}
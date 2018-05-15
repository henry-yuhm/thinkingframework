package org.thinking.logistics.services.core.service.stagingarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.stagingarea.QStagingareaConfiguration;
import org.thinking.logistics.services.core.domain.stagingarea.StagingareaConfiguration;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@Service
public class StagingareaConfigurationService extends DomainService<QStagingareaConfiguration, StagingareaConfiguration, Long> {
    @Autowired
    public StagingareaConfigurationService(EntityManager entityManager, DomainRepository<StagingareaConfiguration, Long> repository) {
        super(entityManager, repository, QStagingareaConfiguration.stagingareaConfiguration);
    }

    public final StagingareaConfiguration acquire(OutboundOrderHeader header, boolean verifiable) throws Exception {
        StagingareaConfiguration configuration = this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(header.getWarehouse()),
                this.getPath().owner.eq(header.getOwner()),
                this.getPath().takegoodsMode.eq(header.getTakegoodsMode()))
            .fetchOne();

        if (verifiable) {
            if (configuration == null) {
                throw CompositeException.getException("月台配置参数未设定", header, header.getOwner());
            }

            if (configuration.getSmallQuantity().compareTo(BigDecimal.ZERO) == 0 || configuration.getLargeQuantity().compareTo(BigDecimal.ZERO) == 0) {
                throw CompositeException.getException("提货方式【" + header.getTakegoodsMode().toString() + "】对应的月台件数未设定", header, header.getOwner());
            }
        }

        return configuration;
    }
}
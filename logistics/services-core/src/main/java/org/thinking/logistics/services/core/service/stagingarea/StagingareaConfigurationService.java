package org.thinking.logistics.services.core.service.stagingarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;
import org.thinking.logistics.services.core.domain.stagingarea.StagingareaConfiguration;
import org.thinking.logistics.services.core.domain.stagingarea.dsl.QStagingareaConfiguration;
import org.thinking.logistics.services.core.service.EntityService;

import java.math.BigDecimal;

@Service
public class StagingareaConfigurationService extends EntityService<QStagingareaConfiguration, StagingareaConfiguration, Long> {
    @Autowired
    public StagingareaConfigurationService(JpaRepository<StagingareaConfiguration, Long> repository) {
        super(repository);
        this.setProbe(QStagingareaConfiguration.stagingareaConfiguration);
    }

    public final StagingareaConfiguration findOne(OutboundHeader header) throws Exception {
        StagingareaConfiguration configuration = this.getQueryFactory().selectFrom(this.getProbe())
            .where(
                this.getProbe().warehouse.eq(header.getWarehouse()),
                this.getProbe().owner.eq(header.getOwner()),
                this.getProbe().takegoodsMode.eq(header.getTakegoodsMode()))
            .fetchOne();

        if (configuration == null) {
            throw CompositeException.getException("月台配置参数未设定", header, header.getOwner());
        }

        if (configuration.getSmallQuantity().compareTo(BigDecimal.ZERO) == 0 || configuration.getLargeQuantity().compareTo(BigDecimal.ZERO) == 0) {
            throw CompositeException.getException("提货方式【" + header.getTakegoodsMode().toString() + "】对应的月台件数未设定", header, header.getOwner());
        }

        return configuration;
    }
}
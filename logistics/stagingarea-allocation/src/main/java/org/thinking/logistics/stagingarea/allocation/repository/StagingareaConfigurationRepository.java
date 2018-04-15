package org.thinking.logistics.stagingarea.allocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.Warehouse;
import org.thinking.logistics.stagingarea.allocation.entity.StagingareaConfiguration;

@Repository
public interface StagingareaConfigurationRepository extends JpaRepository<StagingareaConfiguration, StagingareaConfiguration.StagingareaConfigurationPk> {
    StagingareaConfiguration findByWarehouseAndOwnerAndTakegoodsMode(Warehouse warehouse, Owner owner, TakegoodsMode takegoodsMode);
}
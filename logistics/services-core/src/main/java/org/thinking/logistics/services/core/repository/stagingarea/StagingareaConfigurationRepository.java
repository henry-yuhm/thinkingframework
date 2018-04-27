package org.thinking.logistics.services.core.repository.stagingarea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.entity.stagingarea.StagingareaConfiguration;

@Repository
public interface StagingareaConfigurationRepository extends JpaRepository<StagingareaConfiguration, Long> {
}
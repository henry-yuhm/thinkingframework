package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Platform;

@Repository
public interface PlatformRepository extends DomainRepository<Platform, Long> {
}
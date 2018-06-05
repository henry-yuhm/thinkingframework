package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.core.Platform;

@Repository
public interface PlatformRepository extends DomainRepository<Platform, Long> {
}
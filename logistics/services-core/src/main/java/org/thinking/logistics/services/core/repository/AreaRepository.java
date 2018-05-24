package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Area;

@Repository
public interface AreaRepository extends DomainRepository<Area, Long> {
}
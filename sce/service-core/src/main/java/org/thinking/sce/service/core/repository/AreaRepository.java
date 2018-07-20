package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Area;

@Repository
public interface AreaRepository extends DomainRepository<Area, Long> {
}
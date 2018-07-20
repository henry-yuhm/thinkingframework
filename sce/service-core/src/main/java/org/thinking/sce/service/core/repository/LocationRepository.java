package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Location;

@Repository
public interface LocationRepository extends DomainRepository<Location, Long> {
}
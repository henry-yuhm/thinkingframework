package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Location;

@Repository
public interface LocationRepository extends DomainRepository<Location, Long> {
}
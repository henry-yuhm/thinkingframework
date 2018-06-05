package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.core.Direction;

@Repository
public interface DirectionRepository extends DomainRepository<Direction, Long> {
}
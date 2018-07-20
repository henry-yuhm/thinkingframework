package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Direction;

@Repository
public interface DirectionRepository extends DomainRepository<Direction, Long> {
}
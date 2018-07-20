package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Route;

@Repository
public interface RouteRepository extends DomainRepository<Route, Long> {
}
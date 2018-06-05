package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.core.Route;

@Repository
public interface RouteRepository extends DomainRepository<Route, Long> {
}
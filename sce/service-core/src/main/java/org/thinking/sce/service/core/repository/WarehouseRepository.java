package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Warehouse;

@Repository
public interface WarehouseRepository extends DomainRepository<Warehouse, Long> {
}
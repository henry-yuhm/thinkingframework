package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Warehouse;

@Repository
public interface WarehouseRepository extends DomainRepository<Warehouse, Long> {
}
package org.thinking.sce.service.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ShipmentOrderRepository extends DomainRepository<ShipmentOrderHeader, Long> {
}
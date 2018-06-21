package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ShipmentOrderRepository extends DomainRepository<ShipmentOrderHeader, Long> {
}
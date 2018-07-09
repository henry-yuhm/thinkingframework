package org.thinking.logistics.services.core.repository.document;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.document.MoveOrderHeader;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface MoveOrderRepository extends DomainRepository<MoveOrderHeader, Long> {
}
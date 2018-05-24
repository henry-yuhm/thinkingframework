package org.thinking.logistics.services.core.repository.table;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.table.RecheckBuffer;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface RecheckBufferRepository extends DomainRepository<RecheckBuffer, Long> {
}
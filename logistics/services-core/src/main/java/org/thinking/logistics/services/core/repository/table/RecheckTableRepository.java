package org.thinking.logistics.services.core.repository.table;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.table.RecheckTable;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface RecheckTableRepository extends DomainRepository<RecheckTable, Long> {
}
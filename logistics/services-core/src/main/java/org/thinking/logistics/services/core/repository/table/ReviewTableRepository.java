package org.thinking.logistics.services.core.repository.table;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.table.ReviewTable;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReviewTableRepository extends DomainRepository<ReviewTable, Long> {
}
package org.thinking.sce.service.core.repository.table;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.table.ReviewTable;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ReviewTableRepository extends DomainRepository<ReviewTable, Long> {
}
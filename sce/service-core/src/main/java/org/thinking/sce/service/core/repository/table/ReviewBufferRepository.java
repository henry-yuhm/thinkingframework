package org.thinking.sce.service.core.repository.table;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.table.ReviewBuffer;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ReviewBufferRepository extends DomainRepository<ReviewBuffer, Long> {
}
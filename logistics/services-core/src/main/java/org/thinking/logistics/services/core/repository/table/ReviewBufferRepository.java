package org.thinking.logistics.services.core.repository.table;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.table.ReviewBuffer;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReviewBufferRepository extends DomainRepository<ReviewBuffer, Long> {
}
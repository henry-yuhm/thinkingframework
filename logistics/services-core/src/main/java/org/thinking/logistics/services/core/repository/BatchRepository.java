package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.core.Batch;

@Repository
public interface BatchRepository extends DomainRepository<Batch, Long> {
}
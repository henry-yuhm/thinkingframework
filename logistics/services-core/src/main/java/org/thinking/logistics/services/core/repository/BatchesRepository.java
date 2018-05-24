package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Batches;

@Repository
public interface BatchesRepository extends DomainRepository<Batches, Long> {
}
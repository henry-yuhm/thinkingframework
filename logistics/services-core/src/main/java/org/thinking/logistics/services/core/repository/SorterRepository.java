package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.common.Sorter;

@Repository
public interface SorterRepository extends DomainRepository<Sorter, Long> {
}
package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Sorter;

@Repository
public interface SorterRepository extends DomainRepository<Sorter, Long> {
}
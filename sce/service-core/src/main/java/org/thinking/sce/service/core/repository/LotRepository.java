package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Lot;

@Repository
public interface LotRepository extends DomainRepository<Lot, Long> {
}
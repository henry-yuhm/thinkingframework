package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.core.Lot;

@Repository
public interface LotRepository extends DomainRepository<Lot, Long> {
}
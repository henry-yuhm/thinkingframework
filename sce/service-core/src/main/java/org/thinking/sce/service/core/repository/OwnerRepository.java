package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Owner;

@Repository
public interface OwnerRepository extends DomainRepository<Owner, Long> {
}
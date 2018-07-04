package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.common.Owner;

@Repository
public interface OwnerRepository extends DomainRepository<Owner, Long> {
}
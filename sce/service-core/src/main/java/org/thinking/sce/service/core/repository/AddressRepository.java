package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Address;

@Repository
public interface AddressRepository extends DomainRepository<Address, Long> {
}
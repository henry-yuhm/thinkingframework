package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Address;

@Repository
public interface AddressRepository extends DomainRepository<Address, Long> {
}
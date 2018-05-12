package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Customer;
import org.thinking.logistics.services.core.domain.Owner;

@Repository
public interface CustomerRepository extends DomainRepository<Customer, Long> {
    Customer findByOwnerAndNo(Owner owner, String no);
}
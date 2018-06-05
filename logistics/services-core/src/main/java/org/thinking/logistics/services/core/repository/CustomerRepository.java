package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.core.Customer;

@Repository
public interface CustomerRepository extends DomainRepository<Customer, Long> {
}
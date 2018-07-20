package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Customer;

@Repository
public interface CustomerRepository extends DomainRepository<Customer, Long> {
}
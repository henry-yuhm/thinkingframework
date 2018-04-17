package org.thinking.logistics.services.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.entity.Customer;
import org.thinking.logistics.services.core.entity.Owner;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByOwnerAndNumber(Owner owner, String number);
}
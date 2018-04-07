package org.thinking.logistics.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.core.entity.Address;
import org.thinking.logistics.core.entity.Customer;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByCustomer(Customer customer);
}
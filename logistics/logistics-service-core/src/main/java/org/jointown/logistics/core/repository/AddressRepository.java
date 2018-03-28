package org.jointown.logistics.core.repository;

import org.jointown.logistics.core.entity.Address;
import org.jointown.logistics.core.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByCustomer(Customer customer);
}
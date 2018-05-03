package org.thinking.logistics.services.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.Customer;
import org.thinking.logistics.services.core.domain.Owner;
import org.thinking.logistics.services.core.domain.dsl.QCustomer;

@Service
public class CustomerService extends EntityService<QCustomer, Customer, Long> {
    @Autowired
    public CustomerService(JpaRepository<Customer, Long> repository) {
        super(repository);
        this.setPath(QCustomer.customer);
    }

    public final Customer findOne(Owner owner, String no) {
        this.getPredicates().add(this.getPath().owner.eq(owner));
        this.getPredicates().add(this.getPath().no.eq(no));

        return this.getOne();
    }
}
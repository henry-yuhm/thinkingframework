package org.thinking.sce.service.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.common.Customer;
import org.thinking.sce.service.core.domain.common.Owner;
import org.thinking.sce.service.core.domain.common.QCustomer;
import org.thinking.sce.service.core.repository.DomainRepository;

import javax.persistence.EntityManager;

@Service
public class CustomerService extends DomainService<QCustomer, Customer, Long> {
    @Autowired
    public CustomerService(EntityManager entityManager, DomainRepository<Customer, Long> repository) {
        super(entityManager, repository, QCustomer.customer);
    }

    public final Customer acquire(Owner owner, String no, boolean verifiable) throws Exception {
        Customer customer = this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().owner.eq(owner),
                this.getPath().no.eq(no))
            .fetchOne();

        if (verifiable) {
            if (customer == null) {
                throw CompositeException.getException(owner.toString() + "无此编号【" + no + "】的客户资料");
            }
        }

        return customer;
    }

    public final void save(Customer c, boolean verifiable) throws Exception {
        Customer customer = this.acquire(c.getOwner(), c.getNo(), verifiable);

        if (customer == null) {
            this.getRepository().save(c);
        } else {
            customer.setMnemonicCode(c.getMnemonicCode());

            this.getRepository().save(customer);
        }
    }
}
package org.thinking.sce.service.core.service;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.common.Address;
import org.thinking.sce.service.core.domain.common.Customer;
import org.thinking.sce.service.core.domain.common.QAddress;
import org.thinking.sce.service.core.repository.DomainRepository;

import javax.persistence.EntityManager;

@Service
public class AddressService extends DomainService<QAddress, Address, Long> {
    @Autowired
    public AddressService(EntityManager entityManager, DomainRepository<Address, Long> repository) {
        super(entityManager, repository, QAddress.address);
    }

    public final Address acquire(Customer customer, boolean verifiable) throws Exception {
        JPAQuery<Address> query = this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().customer.eq(customer),
                this.getPath().defaults.isTrue());

        if (query.fetchCount() > 1) {
            throw CompositeException.getException("不能配置多个默认配送地址", customer);
        }

        Address address = query.fetchOne();

        if (verifiable) {
            if (address == null) {
                throw CompositeException.getException("未配置默认配送地址", customer);
            }

            if (address.getDirection() == null) {
                throw CompositeException.getException("默认配送地址未配置配送方向", customer);
            }
        }

        return address;
    }
}
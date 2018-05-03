package org.thinking.logistics.services.core.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.Address;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.Customer;
import org.thinking.logistics.services.core.domain.dsl.QAddress;

import java.util.List;

@Service
public class AddressService extends EntityService<QAddress, Address, Long> {
    public AddressService(JpaRepository<Address, Long> repository) {
        super(repository);
        this.setProbe(QAddress.address);
    }

    public final Address findOne(Customer customer) throws Exception {
        List<Address> addresses = this.getQueryFactory().selectFrom(this.getProbe())
            .where(
                this.getProbe().customer.eq(customer),
                this.getProbe().defaults.isTrue())
            .fetch();

        if (addresses == null || addresses.size() == 0) {
            throw CompositeException.getException("未配置默认配送地址", customer);
        }

        if (addresses.size() > 1) {
            throw CompositeException.getException("不能配置多个默认配送地址", customer);
        }

        if (addresses.get(0).getDirection() == null) {
            throw CompositeException.getException("默认配送地址未配置配送方向", customer);
        }

        return addresses.get(0);
    }
}
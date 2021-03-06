package org.thinking.sce.data.servo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.common.Customer;
import org.thinking.sce.service.core.domain.common.QCustomer;
import org.thinking.sce.service.core.service.CustomerService;

import javax.annotation.Resource;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerReceiver extends AbstractReceiver {
    private final QCustomer qCustomer = QCustomer.customer;

    private final Customer customer;

    @Resource
    private CustomerService service;

    public CustomerReceiver(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void verify() throws Exception {
        if (this.customer == null) {
            throw CompositeException.getException("客户资料未传输成功");
        }

//        this.customer.setOwner(Optional.of(this.customer.getOwner()).orElseThrow(() -> CompositeException.getException("业主未指定")).getOne(this.customer.getOwner(), NotExistsEntityException.OWNER));
    }

    @Override
    public void save() throws Exception {
        this.service.save(this.customer, false);
    }
}
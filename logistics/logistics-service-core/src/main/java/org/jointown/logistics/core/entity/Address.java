package org.jointown.logistics.core.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Customer customer;//客户

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Direction direction;//方向

    @OneToOne(fetch = FetchType.LAZY)
    private Route route;//路线

    private String address;//地址

    private BigInteger loadingOrder;//装车顺序

    private String code;//编码

    @Column(nullable = false)
    private boolean defaultAddress;//是否默认地址

    private String contactName;//联系人

    private String contactPhone;//联系电话

    public Address() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getLoadingOrder() {
        return loadingOrder;
    }

    public void setLoadingOrder(BigInteger loadingOrder) {
        this.loadingOrder = loadingOrder;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
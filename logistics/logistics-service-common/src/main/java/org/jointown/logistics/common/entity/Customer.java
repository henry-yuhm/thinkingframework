package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_customer_owner"))
    private Owner owner;

    private String no;

    private String sourceCode;

    private Customer superCustomer;

    private String mnemonicCode;

    private String name;

    private String shortName;

    private String address;

    private String phone;

    private String warehouseAddress;

    private String sign;

    private String identifier;

    private String location;

    private String lotRequest;

    private String category;

    private String grade;

    private String district;

    private String businessman;
}
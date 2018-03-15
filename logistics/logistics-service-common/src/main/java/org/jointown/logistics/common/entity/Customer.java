package org.jointown.logistics.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Customer implements Serializable {
    @Id
    private long id;

    @OneToOne
    private Owner owner;

    private String code;

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
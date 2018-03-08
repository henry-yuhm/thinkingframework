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
}
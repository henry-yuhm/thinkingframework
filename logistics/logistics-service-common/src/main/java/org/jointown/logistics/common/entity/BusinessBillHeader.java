package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.domain.Bill;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@MappedSuperclass
public abstract class BusinessBillHeader implements Bill {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @OneToOne(fetch = FetchType.LAZY)
    private Owner owner;

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private String no;

    private Type type;

    private Category category;

    private Stage stage;

    private String operator;

    private boolean passback;

    private int printTimes;

    private String remarks;

    private Date createTime;

    private Date updateTime;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<? extends BusinessBillDetail> details;
}
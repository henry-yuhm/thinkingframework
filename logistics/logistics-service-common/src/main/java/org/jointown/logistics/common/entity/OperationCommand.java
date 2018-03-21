package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.domain.Command;

import javax.persistence.*;

@MappedSuperclass
public abstract class OperationCommand implements Command {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @OneToOne(fetch = FetchType.LAZY)
    private Owner owner;

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    private OperationCommand parentCommand;

}
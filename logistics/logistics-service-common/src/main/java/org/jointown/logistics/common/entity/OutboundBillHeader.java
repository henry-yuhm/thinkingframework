package org.jointown.logistics.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
public class OutboundBillHeader implements Serializable {
    @Id
    private long id;

    @OneToOne
    private Warehouse warehouse;

    @OneToOne
    private Owner owner;

    private String businessBillNo;

    @OneToOne
    private Customer customer;

    @OneToOne
    private Customer customer2nd;

    @OneToOne
    private OutboundBillHeader superBillHeader;

    @OneToOne
    private OutboundBillHeader supplementalBillHeader;

    private String operationStatus;

    private String waveNo;

    private String dispatchingType;

    private String dispatcher;

    private Date dispatchingTime;
}
package org.jointown.logistics.common.entity;

import javax.persistence.Entity;

@Entity
public class OutboundBillHeader {
    private long id;

    private String warehouseNo;

    private Owner owner;

    private String businessBillNo;
}
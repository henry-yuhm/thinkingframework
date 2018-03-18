package org.jointown.logistics.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BillDetail {
    @Id
    @GeneratedValue
    private long id;

    public BillDetail() {
    }
}
package org.jointown.logistics.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class OutboundBillDetail implements Serializable {
    @Id
    private long id;
}
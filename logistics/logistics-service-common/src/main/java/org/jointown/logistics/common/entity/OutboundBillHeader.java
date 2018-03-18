package org.jointown.logistics.common.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
public class OutboundBillHeader extends BillHeader implements Serializable {
    private String waveNo;

    private String dispatchingType;

    private String dispatcher;

    private Date dispatchingTime;
}
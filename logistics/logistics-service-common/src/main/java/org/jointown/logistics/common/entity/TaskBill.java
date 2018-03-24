package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TaskBill {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_taskbill_warehouse"))
    private Warehouse warehouse;

    private String picker;

    private Date pickingStartTime;

    private Date pickingCompleteTime;

    private String rechecker;

    private Date recheckStartTime;

    private Date recheckCompleteTime;
}
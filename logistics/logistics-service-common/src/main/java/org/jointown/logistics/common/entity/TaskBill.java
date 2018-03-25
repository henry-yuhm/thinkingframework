package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TaskBill {
    @Id
    @TableGenerator(name = "TaskBillId", table = "TaskBillId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TaskBillId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_taskbill_warehouse"))
    private Warehouse warehouse;//仓库

    private String picker;//拣货员

    private Date pickingStartTime;//拣货开始时间

    private Date pickingCompleteTime;//拣货完成时间

    private String rechecker;//复核员

    private Date recheckStartTime;//复核开始时间

    private Date recheckCompleteTime;//复核完成时间
}
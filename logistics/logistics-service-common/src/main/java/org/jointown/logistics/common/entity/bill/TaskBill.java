package org.jointown.logistics.common.entity.bill;

import org.jointown.logistics.common.entity.Warehouse;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class TaskBill {
    @Id
    @TableGenerator(name = "TaskBillId", table = "TaskBillId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TaskBillId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tb_wh"))
    private Warehouse warehouse;//仓库

    private String picker;//拣货员

    private Time pickingStartTime;//拣货开始时间

    private Time pickingCompleteTime;//拣货完成时间

    private String rechecker;//复核员

    private Time recheckStartTime;//复核开始时间

    private Time recheckCompleteTime;//复核完成时间
}
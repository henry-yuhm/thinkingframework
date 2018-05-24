package org.thinking.logistics.services.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.domain.container.Totebox;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.table.RecheckBuffer;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    private String splittingBill;//拆分单

    @ManyToOne(fetch = FetchType.LAZY)
    private Totebox totebox;//周转箱

    @ManyToOne(fetch = FetchType.LAZY)
    private RecheckBuffer buffer;//复核暂存位

    private int batchNo;//任务批次号

    private String bufferNo;//暂存位编号

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee picker;//拣货员

    private Date pickingStartTime;//拣货开始时间

    private Date pickingCompleteTime;//拣货完成时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee rechecker;//复核员

    private Date recheckStartTime;//复核开始时间

    private Date recheckCompleteTime;//复核完成时间
}
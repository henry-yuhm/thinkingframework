package org.thinking.logistics.services.core.entity.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.entity.container.Totebox;
import org.thinking.logistics.services.core.entity.table.RecheckBuffer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class RemainderTask extends Task {
    private String splittingBill;//拆分单

    @ManyToOne(fetch = FetchType.LAZY)
    private Totebox totebox;//周转箱

    @ManyToOne(fetch = FetchType.LAZY)
    private RecheckBuffer buffer;//复核暂存位

    private int batchNumber;//任务批次号

    private String bufferNumber;//暂存位编号

    private String rechecker;//复核员

    private Date recheckStartTime;//复核开始时间

    private Date recheckCompleteTime;//复核完成时间
}
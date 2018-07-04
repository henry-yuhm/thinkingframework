package org.thinking.logistics.services.core.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.container.Totebox;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.table.ReviewBuffer;

import javax.persistence.*;
import java.time.Instant;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Task extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false, updatable = false, length = 20)
    private String no;//编号

    @Column(length = 100)
    private String splittingBill;//拆分单

    @ManyToOne(fetch = FetchType.LAZY)
    private Totebox totebox;//周转箱

    @ManyToOne(fetch = FetchType.LAZY)
    private ReviewBuffer buffer;//复核暂存位

    private int groupNo;//任务组

    @Column(updatable = false, length = 1)
    private String bufferNo;//暂存位编号

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee picker;//拣货员

    @Temporal(TemporalType.DATE)
    private Instant pickingStartTime;//拣货开始时间

    @Temporal(TemporalType.DATE)
    private Instant pickingCompleteTime;//拣货完成时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee reviewer;//复核员

    @Temporal(TemporalType.DATE)
    private Instant reviewStartTime;//复核开始时间

    @Temporal(TemporalType.DATE)
    private Instant reviewCompleteTime;//复核完成时间
}
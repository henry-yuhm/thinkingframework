package org.thinking.logistics.services.core.domain.scheduler;

import lombok.Data;
import org.thinking.logistics.services.core.domain.Warehouse;
import org.thinking.logistics.services.core.domain.support.SchedulerType;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@MappedSuperclass
@Data
public abstract class Scheduler {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private SchedulerType type;//类型

    @Column(nullable = false)
    private boolean complete = false;//完成

    @Column(nullable = false)
    private Date creationTime = Date.valueOf(LocalDate.now());//创建时间

    @Column(nullable = false)
    private Date modificationTime = Date.valueOf(LocalDate.now());//修改时间
}
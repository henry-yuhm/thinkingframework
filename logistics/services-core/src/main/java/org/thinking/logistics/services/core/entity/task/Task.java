package org.thinking.logistics.services.core.entity.task;

import lombok.Data;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;
import java.sql.Date;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Task {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    private String picker;//拣货员

    private Date pickingStartTime;//拣货开始时间

    private Date pickingCompleteTime;//拣货完成时间
}
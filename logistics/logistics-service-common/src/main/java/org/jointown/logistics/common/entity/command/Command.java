package org.jointown.logistics.common.entity.command;

import org.jointown.logistics.common.entity.Warehouse;
import org.jointown.logistics.common.entity.support.*;

import javax.persistence.*;
import java.sql.Time;

@MappedSuperclass
public abstract class Command {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;//仓库

    private PackageSign sign;//包装标识

    private BusinessType type;//业务类型

    private OperationCategory category;//作业类别

    private TaskStage stage;//任务阶段

    private OperationMode mode;//作业方式

    private Time creationTime;//创建时间

    private Time modificationTime;//修改时间
}
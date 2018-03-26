package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.*;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Command {
    @Id
    @TableGenerator(name = "CommandId", table = "CommandId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CommandId")
    protected long id;

    @OneToOne(fetch = FetchType.LAZY)
    protected Warehouse warehouse;//仓库

    @OneToOne(fetch = FetchType.LAZY)
    protected Owner owner;//业主

    @OneToOne(fetch = FetchType.LAZY)
    protected Customer customer;//客户

    @OneToOne(fetch = FetchType.LAZY)
    protected Command parent;//父指令

    @OneToOne(fetch = FetchType.LAZY)
    protected BillHeader billHeader;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    protected BillDetail billDetail;//单据明细

    @OneToOne(fetch = FetchType.LAZY)
    protected Goods goods;//商品

    @OneToOne(fetch = FetchType.LAZY)
    protected BatchNumber batchNumber;//批号

    protected PackageSign packageSign;//包装标识

    protected BusinessType businessType;//业务类型

    protected OperationCategory operationCategory;//作业类别

    protected TaskStage taskStage;//任务阶段

    protected OperationMode operationMode;//作业方式

    @OneToOne(fetch = FetchType.LAZY)
    protected OperationBarcode operationBarcode;//作业条码

    protected Date creationTime;//创建时间

    protected Date modificationTime;//修改时间
}
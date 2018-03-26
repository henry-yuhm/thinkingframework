package org.jointown.logistics.common.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BillDetail {
    @Id
    @TableGenerator(name = "detailId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "detailId")
    protected long id;

    @OneToOne(fetch = FetchType.LAZY)
    protected Warehouse warehouse;//仓库

    @OneToOne(fetch = FetchType.LAZY)
    protected BillDetail parent;//父单据明细

    @OneToOne(fetch = FetchType.LAZY)
    protected BillHeader billHeader;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    protected Goods goods;//商品

    @OneToOne(fetch = FetchType.LAZY)
    protected BatchNumber batchNumber;//批号
}
package org.jointown.logistics.common.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BillDetail {
    @Id
    @TableGenerator(name = "detailId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "detailId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;//仓库

    @OneToOne(fetch = FetchType.LAZY)
    private BillDetail parentBillDetail;//父单据明细

    @OneToOne(fetch = FetchType.LAZY)
    private BillHeader billHeader;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    private Goods goods;//商品

    @OneToOne(fetch = FetchType.LAZY)
    private BatchNumber batchNumber;//批号

    BillDetail() {
    }
}
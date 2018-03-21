package org.jointown.logistics.common.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BillDetail {
    @Id
    @TableGenerator(name = "detailId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "detailId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @OneToOne(fetch = FetchType.LAZY)
    private BillDetail parentBillDetail;

    @OneToOne(fetch = FetchType.LAZY)
    private BillHeader billHeader;

    @OneToOne(fetch = FetchType.LAZY)
    private Goods goods;

    @OneToOne(fetch = FetchType.LAZY)
    private BatchNumber batchNumber;

    BillDetail() {
    }
}
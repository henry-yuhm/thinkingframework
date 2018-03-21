package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.domain.Bill;

import javax.persistence.*;

@MappedSuperclass
public abstract class BusinessBillDetail implements Bill {
    @Id
    @TableGenerator(name = "detailId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "detailId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private BusinessBillDetail parentBillDetail;

    @OneToOne(fetch = FetchType.LAZY)
    private Goods goods;

    @OneToOne(fetch = FetchType.LAZY)
    private BatchNumber batchNumber;

    protected BusinessBillDetail() {
    }
}
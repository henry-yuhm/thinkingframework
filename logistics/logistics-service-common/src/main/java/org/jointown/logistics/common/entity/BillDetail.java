package org.jointown.logistics.common.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BillDetail {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_parent_bill_detail"))
    private BillDetail parentBillDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_goods"))
    private Goods goods;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_batch_number"))
    private BatchNumber batchNumber;

    public BillDetail() {
    }
}
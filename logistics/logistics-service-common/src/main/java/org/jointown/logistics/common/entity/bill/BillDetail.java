package org.jointown.logistics.common.entity.bill;

import org.jointown.logistics.common.entity.BatchNumber;
import org.jointown.logistics.common.entity.Goods;
import org.jointown.logistics.common.entity.Warehouse;

import javax.persistence.*;

@MappedSuperclass
public abstract class BillDetail {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;//仓库

    @OneToOne(fetch = FetchType.LAZY)
    private Goods goods;//商品

    @OneToOne(fetch = FetchType.LAZY)
    private BatchNumber batchNumber;//批号

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public BatchNumber getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(BatchNumber batchNumber) {
        this.batchNumber = batchNumber;
    }
}
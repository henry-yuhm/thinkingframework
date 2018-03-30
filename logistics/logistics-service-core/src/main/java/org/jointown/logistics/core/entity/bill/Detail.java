package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.Batch;
import org.jointown.logistics.core.entity.Goods;
import org.jointown.logistics.core.entity.Warehouse;

import javax.persistence.*;

@MappedSuperclass
public abstract class Detail implements Details {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Batch batch;//批号

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

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }
}
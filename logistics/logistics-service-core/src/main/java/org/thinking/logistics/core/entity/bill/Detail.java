package org.thinking.logistics.core.entity.bill;

import lombok.Data;
import org.thinking.logistics.core.entity.Batch;
import org.thinking.logistics.core.entity.Goods;
import org.thinking.logistics.core.entity.Warehouse;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Detail {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @ManyToOne(fetch = FetchType.LAZY)
    private Batch batch;//批号

//    public abstract Header getHeader();
}
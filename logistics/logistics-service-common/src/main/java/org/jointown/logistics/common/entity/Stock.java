package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class Stock {
    @Id
    @TableGenerator(name = "StockId", table = "StockId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "StockId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;//仓库

}
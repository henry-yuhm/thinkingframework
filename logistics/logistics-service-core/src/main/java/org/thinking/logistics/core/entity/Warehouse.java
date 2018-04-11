package org.thinking.logistics.core.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Warehouse {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false)
    private String name;//名称

    @Column(nullable = false)
    private boolean electronicLabel;//是否有电子标签

    @Column(nullable = false)
    private boolean pallet;//是否使用托盘

    @Column(nullable = false)
    private boolean sorter;//是否有分拣机

    @Column(nullable = false)
    private boolean tablet;//是否使用平板电脑

//    private TransferlineSigns[] transferlineSigns;//输送线标识
//
//    private TWFSign[] twfSigns;//立体库标识

    private String address;//地址
}
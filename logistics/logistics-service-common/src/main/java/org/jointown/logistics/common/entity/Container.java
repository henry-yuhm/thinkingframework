package org.jointown.logistics.common.entity;

import javax.persistence.*;

@MappedSuperclass
public class Container {
    @Id
    @TableGenerator(name = "ContainerId", table = "ContainerId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ContainerId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;//仓库

    private String no;//编号

    private boolean available;//是否可用
}
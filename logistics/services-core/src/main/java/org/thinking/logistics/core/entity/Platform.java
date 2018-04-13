package org.thinking.logistics.core.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Platform {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @Column(nullable = false)
    private String name;//名称
}
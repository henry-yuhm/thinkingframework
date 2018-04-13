package org.thinking.logistics.services.core.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Route {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String number;//编号

    @Column(nullable = false)
    private String name;//名称

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Direction direction;//方向
}
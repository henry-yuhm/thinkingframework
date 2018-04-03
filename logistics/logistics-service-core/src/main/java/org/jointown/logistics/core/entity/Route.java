package org.jointown.logistics.core.entity;

import javax.persistence.*;

@Entity
public class Route {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String name;//名称

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Direction direction;//方向

    public Route() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
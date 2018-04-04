package org.jointown.logistics.core.entity;

import javax.persistence.*;

@Entity
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

    public Route() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
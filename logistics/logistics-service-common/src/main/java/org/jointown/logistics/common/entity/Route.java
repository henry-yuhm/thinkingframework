package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class Route {
    @Id
    @GeneratedValue
    private long id;

    private String no;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_route_direction"))
    private Direction direction;

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
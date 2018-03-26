package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class Route {
    @Id
    @TableGenerator(name = "RouteId", table = "RouteId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RouteId")
    private long id;

    private String no;//编号

    private String name;//名称

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_rut_dir"))
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
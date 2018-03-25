package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class Direction {
    @Id
    @TableGenerator(name = "DirectionId", table = "DirectionId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DirectionId")
    private long id;

    private String no;//编号

    private String name;//名称

    public Direction() {
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
}
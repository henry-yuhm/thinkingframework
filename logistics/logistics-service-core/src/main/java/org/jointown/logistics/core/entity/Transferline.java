package org.jointown.logistics.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transferline {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String name;//名称

    public Transferline() {
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
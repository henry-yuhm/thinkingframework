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
    private String number;//编号

    @Column(nullable = false)
    private String name;//名称

    public Transferline() {
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
}
package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class Transferline {
    @Id
    @TableGenerator(name = "TransferlineId", table = "TransferlineId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TransferlineId")
    private long id;

    private String no;//编号

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
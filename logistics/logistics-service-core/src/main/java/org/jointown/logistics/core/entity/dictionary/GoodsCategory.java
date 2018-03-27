package org.jointown.logistics.core.entity.dictionary;

import javax.persistence.*;

@Entity
@Table(schema = "wms")
public class GoodsCategory {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String name;//名称

    public GoodsCategory() {
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
package org.jointown.logistics.core.entity.container;

import org.jointown.logistics.core.entity.Warehouse;

import javax.persistence.*;

@MappedSuperclass
public class Container {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private boolean available;//是否可用

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
package org.jointown.logistics.core.entity.table;

import org.jointown.logistics.core.entity.Warehouse;

import javax.persistence.*;

@Entity
public class RecheckSlide {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private RecheckTable table;//复核台

    public RecheckSlide() {
    }

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

    public RecheckTable getTable() {
        return table;
    }

    public void setTable(RecheckTable table) {
        this.table = table;
    }
}
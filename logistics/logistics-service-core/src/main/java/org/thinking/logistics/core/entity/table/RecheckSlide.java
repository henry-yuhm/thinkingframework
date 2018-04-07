package org.thinking.logistics.core.entity.table;

import org.thinking.logistics.core.entity.Warehouse;

import javax.persistence.*;

@Entity
public class RecheckSlide {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RecheckTable table;//复核台

    public RecheckSlide() {
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public RecheckTable getTable() {
        return table;
    }

    public void setTable(RecheckTable table) {
        this.table = table;
    }
}
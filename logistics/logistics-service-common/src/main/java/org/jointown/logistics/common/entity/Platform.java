package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class Platform {
    @Id
    @TableGenerator(name = "PlatformId", table = "PlatformId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PlatformId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pfm_wh"))
    private Warehouse warehouse;//仓库

    private String no;//编号

    private String name;//名称

    public Platform() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
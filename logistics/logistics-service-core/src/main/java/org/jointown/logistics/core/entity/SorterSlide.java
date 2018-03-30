package org.jointown.logistics.core.entity;

import org.jointown.logistics.core.entity.support.SorterSlideCategory;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class SorterSlide {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private SorterSlideCategory category;//类别

    @Column(nullable = false)
    private boolean available;//是否可用

    @Column(nullable = false)
    private BigInteger workload;//工作量

    public SorterSlide() {
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

    public SorterSlideCategory getCategory() {
        return category;
    }

    public void setCategory(SorterSlideCategory category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public BigInteger getWorkload() {
        return workload;
    }

    public void setWorkload(BigInteger workload) {
        this.workload = workload;
    }
}
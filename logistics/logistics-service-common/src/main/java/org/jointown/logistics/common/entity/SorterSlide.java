package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class SorterSlide {
    @Id
    @TableGenerator(name = "sorterSlideId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sorterSlideId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;//仓库

    private String no;//编号

    private Category category;//类别

    private boolean available;//是否可用

    @Column(nullable = false)
    private BigInteger workload = BigInteger.ZERO;//工作量

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    public enum Category {
        ;

        private final String name;

        private final int ordinal;

        Category(String name, int ordinal) {
            this.name = name;
            this.ordinal = ordinal;
        }
    }
}
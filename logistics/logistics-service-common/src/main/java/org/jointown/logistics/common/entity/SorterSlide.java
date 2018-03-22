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
    private Warehouse warehouse;

    private String no;

    private Category category;

    private boolean available;

    @Column(nullable = false)
    private BigInteger workload = BigInteger.ZERO;

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
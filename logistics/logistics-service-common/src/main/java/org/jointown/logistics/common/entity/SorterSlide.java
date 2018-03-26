package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.SorterSlideCategory;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class SorterSlide {
    @Id
    @TableGenerator(name = "SorterSlideId", table = "SorterSlideId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SorterSlideId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_sts_wh"))
    private Warehouse warehouse;//仓库

    private String no;//编号

    private SorterSlideCategory slideCategory;//类别

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

    public SorterSlideCategory getSlideCategory() {
        return slideCategory;
    }

    public void setSlideCategory(SorterSlideCategory slideCategory) {
        this.slideCategory = slideCategory;
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
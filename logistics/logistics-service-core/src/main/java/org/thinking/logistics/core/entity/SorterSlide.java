package org.thinking.logistics.core.entity;

import org.thinking.logistics.core.domain.support.BillCategory;
import org.thinking.logistics.core.domain.support.TaskCategory;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class SorterSlide {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @Column(nullable = false)
    private boolean available = true;//是否可用

    @Column(nullable = false)
    private int workload = 0;//工作量

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "slide_id"), inverseJoinColumns = @JoinColumn(name = "category"))
    private Set<TaskCategory> taskCategories = new LinkedHashSet<>();//任务类别

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "slide_id"), inverseJoinColumns = @JoinColumn(name = "category"))
    private Set<BillCategory> billCategories = new LinkedHashSet<>();//单据类别

    public SorterSlide() {
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public Set<TaskCategory> getTaskCategories() {
        return taskCategories;
    }

    public void setTaskCategories(Set<TaskCategory> taskCategories) {
        this.taskCategories = taskCategories;
    }

    public Set<BillCategory> getBillCategories() {
        return billCategories;
    }

    public void setBillCategories(Set<BillCategory> billCategories) {
        this.billCategories = billCategories;
    }
}
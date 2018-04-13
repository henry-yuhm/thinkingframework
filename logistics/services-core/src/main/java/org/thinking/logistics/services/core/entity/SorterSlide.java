package org.thinking.logistics.services.core.entity;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.BillCategory;
import org.thinking.logistics.services.core.domain.support.CommandCategory;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class SorterSlide {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @Column(nullable = false)
    private boolean available = true;//可用

    @Column(nullable = false)
    private int workload = 0;//工作量

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "slide_id"), inverseJoinColumns = @JoinColumn(name = "category"))
    private Set<CommandCategory> taskCategories = new LinkedHashSet<>();//任务类别

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "slide_id"), inverseJoinColumns = @JoinColumn(name = "category"))
    private Set<BillCategory> billCategories = new LinkedHashSet<>();//单据类别
}
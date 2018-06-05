package org.thinking.logistics.services.core.domain.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.support.BillCategory;
import org.thinking.logistics.services.core.domain.support.CommandCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.LinkedHashMap;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Sorter extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private boolean available = true;//可用

    @Column(nullable = false)
    private int workload = 0;//工作量

    private LinkedHashMap<Integer, CommandCategory> taskCategories = new LinkedHashMap<>();//任务类别

    private LinkedHashMap<Integer, BillCategory> billCategories = new LinkedHashMap<>();//单据类别
}
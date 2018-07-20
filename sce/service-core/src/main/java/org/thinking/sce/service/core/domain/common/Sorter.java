package org.thinking.sce.service.core.domain.common;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.support.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.LinkedHashMap;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Sorter extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false, updatable = false, length = 2)
    private String no;//编号

    @Column(nullable = false)
    private boolean available = true;//可用

    @Column(nullable = false)
    private int workload = 0;//工作量

    private LinkedHashMap<Integer, CommandCategory> taskCategories;//任务类别

    private LinkedHashMap<Integer, ItemClass> billCategories;//单据类别
}
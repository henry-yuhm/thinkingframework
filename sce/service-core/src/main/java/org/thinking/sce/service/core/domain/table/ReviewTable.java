package org.thinking.sce.service.core.domain.table;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.support.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ReviewTable extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private ReviewTableType type = ReviewTableType.NORMAL;//类型

    @Column(nullable = false)
    private ReviewTableCategory category;//类别

    @Column(nullable = false)
    private boolean locking = false;//锁定

    @Column(nullable = false)
    private boolean automatic;//自动化

    @Column(nullable = false)
    private int workload = 0;//工作量

    @Column(nullable = false)
    private int itemQuantity = 0;//品规数
}
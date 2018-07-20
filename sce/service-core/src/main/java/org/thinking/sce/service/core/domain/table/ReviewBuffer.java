package org.thinking.sce.service.core.domain.table;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.support.ReviewBufferType;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ReviewBuffer extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReviewTable table;//复核台

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReviewSlide slide;//复核滑道

    @Column(nullable = false)
    private ReviewBufferType type = ReviewBufferType.NORMAL;//类型

    @Column(nullable = false)
    private boolean available = true;//可用
}
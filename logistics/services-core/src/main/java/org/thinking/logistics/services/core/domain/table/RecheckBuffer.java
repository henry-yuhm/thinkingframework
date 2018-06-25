package org.thinking.logistics.services.core.domain.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.core.Warehouse;
import org.thinking.logistics.services.core.domain.support.RecheckBufferType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class RecheckBuffer extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RecheckTable table;//复核台

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RecheckSlide slide;//复核滑道

    @Column(nullable = false)
    private RecheckBufferType type = RecheckBufferType.NORMAL;//类型

    @Column(nullable = false)
    private boolean available = true;//可用
}
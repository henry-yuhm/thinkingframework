package org.thinking.sce.service.core.domain.scheduler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.support.SchedulerType;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Scheduler extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private SchedulerType type;//类型

    @Column(nullable = false)
    private boolean completed;//完成
}
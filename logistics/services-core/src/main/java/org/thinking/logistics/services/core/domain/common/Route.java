package org.thinking.logistics.services.core.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Route extends BaseDomainEntity {
    @Column(nullable = false, updatable = false, length = 50)
    private String no;//编号

    @Column(nullable = false, length = 100)
    private String name;//名称

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Direction direction;//方向
}
package org.thinking.sce.service.core.domain.common;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Direction extends BaseDomainEntity {
    @Column(nullable = false, updatable = false, length = 50)
    private String no;//编号

    @Column(nullable = false, length = 100)
    private String name;//名称
}
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
public class Company extends BaseDomainEntity {
    @Column(unique = true, nullable = false, length = 50)
    private String no;//编号

    @Column(length = 200)
    private String description;//描述
}
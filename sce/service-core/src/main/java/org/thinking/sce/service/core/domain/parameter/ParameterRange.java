package org.thinking.sce.service.core.domain.parameter;

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
@NoArgsConstructor
public class ParameterRange extends BaseDomainEntity {
    @Column(nullable = false)
    private String warehouse;//仓库

    @Column(nullable = false)
    private String value;//值
}
package org.thinking.sce.service.core.domain.dictionary;

import lombok.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Dictionary extends BaseDomainEntity {
    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String name;//名称

    private String remarks;//备注
}
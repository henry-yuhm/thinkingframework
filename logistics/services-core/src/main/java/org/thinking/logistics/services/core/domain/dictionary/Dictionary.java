package org.thinking.logistics.services.core.domain.dictionary;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Dictionary extends BaseDomainEntity {
    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String name;//名称
}
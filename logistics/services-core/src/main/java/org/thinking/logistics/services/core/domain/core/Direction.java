package org.thinking.logistics.services.core.domain.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Direction extends BaseDomainEntity {
    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String name;//名称
}
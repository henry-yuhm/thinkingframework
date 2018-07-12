package org.thinking.logistics.services.core.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Column;
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
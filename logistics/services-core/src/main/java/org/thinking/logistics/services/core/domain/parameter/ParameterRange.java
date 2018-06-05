package org.thinking.logistics.services.core.domain.parameter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ParameterRange extends BaseDomainEntity {
    @Column(nullable = false)
    private String warehouse;//仓库

    @Column(nullable = false)
    private String value;//值
}
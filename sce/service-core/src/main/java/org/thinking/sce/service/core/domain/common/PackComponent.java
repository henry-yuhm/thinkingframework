package org.thinking.sce.service.core.domain.common;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.support.MeasureUnit;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class PackComponent extends BaseDomainEntity {
    @Column(nullable = false)
    private MeasureUnit measureUnit;//计量单位

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal quantity;//数量
}
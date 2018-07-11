package org.thinking.logistics.services.core.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.support.MeasureUnit;

import javax.persistence.Column;
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
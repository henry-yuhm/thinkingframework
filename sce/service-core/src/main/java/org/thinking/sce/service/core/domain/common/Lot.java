package org.thinking.sce.service.core.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.BaseDomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Lot extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;//商品

    @Column(nullable = false, updatable = false, length = 30)
    private String no;//编号

    @Column(nullable = false)
    private Instant productionDate;//生产日期

    @Column(nullable = false)
    private Instant validUntil;//有效期至

    @Column(length = 30)
    private String printProductionDate;//打印生产日期

    @Column(length = 30)
    private String printValidUntil;//打印有效期至

    @Column(length = 100)
    private String approvalNo;//批准文号
}
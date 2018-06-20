package org.thinking.logistics.services.core.domain.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Lot extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @Column(nullable = false)
    private String no;//编码

    @Column(nullable = false)
    private Date productionDate;//生产日期

    @Column(nullable = false)
    private Date validUntil;//有效期至

    private String printProductionDate;//打印生产日期

    private String printValidUntil;//打印有效期至

    private String approvalNo;//批准文号
}
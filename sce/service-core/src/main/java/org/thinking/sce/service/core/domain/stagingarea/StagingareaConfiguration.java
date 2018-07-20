package org.thinking.sce.service.core.domain.stagingarea;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.*;
import org.thinking.sce.service.core.domain.support.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_sga_cfg", columnNames = {"warehouse_id", "owner_id", "pickupMode"}))
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StagingareaConfiguration extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false)
    private PickupMode pickupMode;//提货方式

    @Column(nullable = false)
    private StagingareaAllocateMode allocationMode;//分配方式

    @Column(nullable = false)
    private BigDecimal smallQuantity;//小订单件数

    @Column(nullable = false)
    private BigDecimal largeQuantity;//大订单件数
}
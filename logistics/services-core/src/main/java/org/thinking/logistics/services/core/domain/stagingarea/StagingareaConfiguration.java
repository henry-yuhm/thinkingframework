package org.thinking.logistics.services.core.domain.stagingarea;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.common.Owner;
import org.thinking.logistics.services.core.domain.common.Warehouse;
import org.thinking.logistics.services.core.domain.support.PickupMode;
import org.thinking.logistics.services.core.domain.support.StagingareaAllocateMode;

import javax.persistence.*;
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
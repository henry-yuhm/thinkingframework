package org.thinking.logistics.services.core.domain.stagingarea;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.core.Owner;
import org.thinking.logistics.services.core.domain.core.Warehouse;
import org.thinking.logistics.services.core.domain.support.PickupMode;
import org.thinking.logistics.services.core.domain.support.StagingareaAllocationMode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_sga_cfg", columnNames = {"warehouse_id", "owner_id", "pickupMode"}))
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
    private StagingareaAllocationMode allocationMode;//分配方式

    @Column(nullable = false)
    private BigDecimal smallQuantity = BigDecimal.ZERO;//小订单件数

    @Column(nullable = false)
    private BigDecimal largeQuantity = BigDecimal.ZERO;//大订单件数
}
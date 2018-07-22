package org.thinking.sce.service.core.domain.stagingarea;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.Direction;
import org.thinking.sce.service.core.domain.common.Owner;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.support.ItemClass;
import org.thinking.sce.service.core.domain.support.PickupMode;
import org.thinking.sce.service.core.domain.support.SaleType;
import org.thinking.sce.service.core.domain.support.StagingareaCategory;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_virtual_sga_cfg", columnNames = {"warehouse_id", "owner_id", "available", "itemClass", "pickupMode", "saleType", "stagingareaCategory", "direction_id"}))
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class VirtualConfiguration extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false)
    private boolean available = true;//可用

    @Column(nullable = false)
    private ItemClass itemClass;//商品种类

    @Column(nullable = false)
    private PickupMode pickupMode;//提货方式

    @Column(nullable = false)
    private SaleType saleType;//销售类型

    @Column(nullable = false)
    private StagingareaCategory stagingareaCategory;//月台类别

    @ManyToOne(fetch = FetchType.LAZY)
    private Direction direction;//方向

    @ManyToOne(fetch = FetchType.LAZY)
    private Stagingarea stagingarea;//月台
}
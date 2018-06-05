package org.thinking.logistics.services.core.domain.stagingarea;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.core.Direction;
import org.thinking.logistics.services.core.domain.core.Owner;
import org.thinking.logistics.services.core.domain.core.Warehouse;
import org.thinking.logistics.services.core.domain.support.BillCategory;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.domain.support.StagingareaCategory;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_virtual_sga_cfg", columnNames = {"warehouse_id", "owner_id", "available", "billCategory", "takegoodsMode", "saleType", "stagingareaCategory", "direction_id"}))
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
    private BillCategory billCategory;//单据类别

    @Column(nullable = false)
    private TakegoodsMode takegoodsMode;//提货方式

    @Column(nullable = false)
    private SaleType saleType;//销售类型

    @Column(nullable = false)
    private StagingareaCategory stagingareaCategory;//月台类别

    @ManyToOne(fetch = FetchType.LAZY)
    private Direction direction;//方向

    @ManyToOne(fetch = FetchType.LAZY)
    private Stagingarea stagingarea;//月台
}
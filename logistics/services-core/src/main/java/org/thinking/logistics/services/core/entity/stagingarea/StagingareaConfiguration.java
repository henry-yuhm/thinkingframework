package org.thinking.logistics.services.core.entity.stagingarea;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.domain.support.StagingareaAllocationMode;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_sga_cfg", columnNames = {"warehouse_id", "owner_id", "takegoodsMode"}))
@Data
@NoArgsConstructor
public class StagingareaConfiguration {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false)
    private TakegoodsMode takegoodsMode;//提货方式

    @Column(nullable = false)
    private StagingareaAllocationMode allocationMode;//分配方式

    @Column(nullable = false)
    private BigDecimal smallQuantity = BigDecimal.ZERO;//小订单件数

    @Column(nullable = false)
    private BigDecimal largeQuantity = BigDecimal.ZERO;//大订单件数
}
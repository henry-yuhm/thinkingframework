package org.thinking.sce.service.core.domain.inventory;

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
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_outbound_configuration", columnNames = {"warehouse_id", "owner_id", "packageType", "itemClass", "saleType", "storeCategory", "storeNo"}))
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OutboundConfiguration extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false)
    private PackageType packageType;//包装类型

    @Column(nullable = false)
    private ItemClass itemClass;//商品种类

    @Column(nullable = false)
    private SaleType saleType;//销售类型

    @Column(nullable = false)
    private String storeCategory;//库别

    @Column(nullable = false)
    private String storeNo;//库房编号

    @Column(nullable = false)
    private BigDecimal threshold;

    @Column(nullable = false)
    private int lowerOrder;

    @Column(nullable = false)
    private int upperOrder;
}
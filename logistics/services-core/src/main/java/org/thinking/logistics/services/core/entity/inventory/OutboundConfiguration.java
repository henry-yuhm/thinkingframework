package org.thinking.logistics.services.core.entity.inventory;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.domain.support.BillCategory;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "lmis", uniqueConstraints = @UniqueConstraint(name = "uk_outbound_configuration", columnNames = {"warehouse_id", "owner_id", "packageType", "billCategory", "saleType", "storeCategory", "storeNo"}))
@Data
@NoArgsConstructor
public class OutboundConfiguration {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false)
    private PackageType packageType;//包装类型

    @Column(nullable = false)
    private BillCategory billCategory;//单据类别

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
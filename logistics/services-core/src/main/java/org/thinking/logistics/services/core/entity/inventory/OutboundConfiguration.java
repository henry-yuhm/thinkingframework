package org.thinking.logistics.services.core.entity.inventory;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.BillCategory;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@IdClass(OutboundConfiguration.PrimaryKey.class)
@Data
public class OutboundConfiguration {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Id
    @Column(nullable = false)
    private PackageType packageType;//包装类型

    @Id
    @Column(nullable = false)
    private BillCategory category;//单据类别

    @Id
    @Column(nullable = false)
    private SaleType saleType;//销售类型

    @Id
    @Column(nullable = false)
    private String storeCategory;//库别

    @Id
    @Column(nullable = false)
    private String storeNo;//库房编号

    @Column(nullable = false)
    private BigDecimal threshold;

    @Column(nullable = false)
    private int lowerOrder;

    @Column(nullable = false)
    private int upperOrder;

    @Data
    public static class PrimaryKey implements Serializable {
        private Warehouse warehouse;//仓库

        private Owner owner;//业主

        private PackageType packageType;//包装类型

        private BillCategory category;//单据类别

        private SaleType saleType;//销售类型

        private String storeCategory;//库别

        private String storeNo;//库房编号
    }
}
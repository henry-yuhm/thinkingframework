package org.thinking.logistics.services.core.entity.stagingarea;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.BillCategory;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.domain.support.StagingareaCategory;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;
import org.thinking.logistics.services.core.entity.Direction;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@IdClass(VirtualStagingareaConfiguration.PrimaryKey.class)
@Data
public class VirtualStagingareaConfiguration {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @ManyToMany
    @JoinTable(
            joinColumns = {
                    @JoinColumn(name = "warehouse_id"),
                    @JoinColumn(name = "owner_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "bill_category"),
                    @JoinColumn(name = "takegoods_mode"),
                    @JoinColumn(name = "sale_type"),
                    @JoinColumn(name = "stagingarea_category"),
                    @JoinColumn(name = "direction"),
                    @JoinColumn(name = "stagingarea"),
                    @JoinColumn(name = "available")}
    )
    private Set<Configuration> configurations;

    @Data
    public static class PrimaryKey implements Serializable {
        private Warehouse warehouse;//仓库

        private Owner owner;//业主

        public PrimaryKey(Warehouse warehouse, Owner owner) {
            this.warehouse = warehouse;
            this.owner = owner;
        }
    }

    @Data
    public static class Configuration implements Serializable {
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
}
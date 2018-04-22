package org.thinking.logistics.services.core.entity.stagingarea;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.StagingareaAllocationMode;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@IdClass(StagingareaConfiguration.PrimaryKey.class)
@Data
public class StagingareaConfiguration {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Id
    @Column(nullable = false)
    private TakegoodsMode takegoodsMode;//提货方式

    @Column(nullable = false)
    private StagingareaAllocationMode mode;//分配方式

    @Column(nullable = false)
    private BigDecimal smallQuantity = BigDecimal.ZERO;//小订单件数

    @Column(nullable = false)
    private BigDecimal largeQuantity = BigDecimal.ZERO;//大订单件数

    @Data
    public static class PrimaryKey implements Serializable {
        private Warehouse warehouse;//仓库

        private Owner owner;//业主

        private TakegoodsMode takegoodsMode;//提货方式

        public PrimaryKey(Warehouse warehouse, Owner owner, TakegoodsMode takegoodsMode) {
            this.warehouse = warehouse;
            this.owner = owner;
            this.takegoodsMode = takegoodsMode;
        }
    }
}
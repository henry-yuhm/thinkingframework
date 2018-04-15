package org.thinking.logistics.stagingarea.allocation.entity;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.StagingareaAllocationMode;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(StagingareaConfiguration.StagingareaConfigurationPk.class)
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
    private int smallQuantity = 0;//小订单件数

    @Column(nullable = false)
    private int largeQuantity = 0;//大订单件数

    @Data
    public static class StagingareaConfigurationPk implements Serializable {
        private Warehouse warehouse;//仓库

        private Owner owner;//业主

        private TakegoodsMode takegoodsMode;//提货方式
    }
}
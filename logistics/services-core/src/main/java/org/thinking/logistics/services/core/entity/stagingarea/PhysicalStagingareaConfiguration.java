package org.thinking.logistics.services.core.entity.stagingarea;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.BillCategory;
import org.thinking.logistics.services.core.domain.support.StagingareaType;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@IdClass(PhysicalStagingareaConfiguration.PrimaryKey.class)
@Data
public class PhysicalStagingareaConfiguration {
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
            @JoinColumn(name = "stagingarea_type")}
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
        private BillCategory billCategory;//单据类别

        @Column(nullable = false)
        private StagingareaType stagingareaType;//月台类型
    }
}
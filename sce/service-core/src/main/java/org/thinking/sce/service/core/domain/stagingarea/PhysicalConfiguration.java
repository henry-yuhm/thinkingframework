package org.thinking.sce.service.core.domain.stagingarea;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.*;
import org.thinking.sce.service.core.domain.support.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_physical_sga_cfg", columnNames = {"warehouse_id", "owner_id", "itemClass"}))
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PhysicalConfiguration extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false)
    private ItemClass itemClass;//商品种类

    @Column(nullable = false)
    private StagingareaType stagingareaType;//月台类型
}
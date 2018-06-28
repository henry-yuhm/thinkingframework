package org.thinking.logistics.services.core.domain.stagingarea;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.core.Owner;
import org.thinking.logistics.services.core.domain.core.Warehouse;
import org.thinking.logistics.services.core.domain.support.ItemCategory;
import org.thinking.logistics.services.core.domain.support.StagingareaType;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_physical_sga_cfg", columnNames = {"warehouse_id", "owner_id", "itemCategory"}))
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
    private ItemCategory itemCategory;//商品类别

    @Column(nullable = false)
    private StagingareaType stagingareaType;//月台类型
}
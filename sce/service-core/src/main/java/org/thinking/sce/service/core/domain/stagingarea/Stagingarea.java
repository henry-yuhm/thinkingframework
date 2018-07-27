package org.thinking.sce.service.core.domain.stagingarea;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.Direction;
import org.thinking.sce.service.core.domain.common.Owner;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.support.DocumentType;
import org.thinking.sce.service.core.domain.support.PickupMode;
import org.thinking.sce.service.core.domain.support.StagingareaCategory;
import org.thinking.sce.service.core.domain.support.StagingareaType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Stagingarea extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    private String name;//名称

    @Column(nullable = false)
    private StagingareaType type = StagingareaType.NORMAL;//类型

    @Column(nullable = false)
    private StagingareaCategory category;//类别

    @Column(nullable = false)
    private boolean locking = false;//锁定

    @Column(nullable = false)
    private boolean available = true;//可用

    private DocumentType documentType;//单据类型

    private PickupMode pickupMode;//提货方式

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "stagingarea_id"), inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private Set<Owner> owners = new LinkedHashSet<>();//业主

    @ManyToOne(fetch = FetchType.LAZY)
    private Direction direction;//方向
}
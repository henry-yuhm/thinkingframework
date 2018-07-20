package org.thinking.sce.service.core.domain.stagingarea;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.*;
import org.thinking.sce.service.core.domain.support.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

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
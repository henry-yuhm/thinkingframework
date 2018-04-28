package org.thinking.logistics.services.core.entity.stagingarea;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.domain.support.BillType;
import org.thinking.logistics.services.core.domain.support.StagingareaCategory;
import org.thinking.logistics.services.core.domain.support.StagingareaType;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;
import org.thinking.logistics.services.core.entity.Direction;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Stagingarea {
    @Id
    @GeneratedValue
    private long id;

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

    private BillType billType;//单据类型

    private TakegoodsMode takegoodsMode;//提货方式

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "stagingarea_id"), inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private Set<Owner> owners = new LinkedHashSet<>();//业主

    @ManyToOne(fetch = FetchType.LAZY)
    private Direction direction;//方向
}
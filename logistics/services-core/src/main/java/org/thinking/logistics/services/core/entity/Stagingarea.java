package org.thinking.logistics.services.core.entity;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.BillKind;
import org.thinking.logistics.services.core.domain.support.StagingareaCategory;
import org.thinking.logistics.services.core.domain.support.StagingareaKind;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class Stagingarea {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    private String name;//名称

    @Column(nullable = false)
    private StagingareaKind kind = StagingareaKind.NORMAL;//类型

    @Column(nullable = false)
    private StagingareaCategory category;//类别

    @Column(nullable = false)
    private boolean available = true;//可用

    private BillKind billKind;//单据类型

    private TakegoodsMode takegoodsMode;//提货方式

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "stagingarea_id"), inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private Set<Owner> owners = new LinkedHashSet<>();//业主

    @ManyToOne(fetch = FetchType.LAZY)
    private Direction direction;//方向
}
package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.BusinessType;
import org.jointown.logistics.common.entity.support.StagingAreaCategory;
import org.jointown.logistics.common.entity.support.StagingAreaType;
import org.jointown.logistics.common.entity.support.TakeMode;

import javax.persistence.*;
import java.util.Set;

@Entity
public class StagingArea {
    @Id
    @TableGenerator(name = "StagingAreaId", table = "StagingAreaId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "StagingAreaId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_sga_warehouse"))
    private Warehouse warehouse;//仓库

    private String no;//编号

    private String name;//名称

    private StagingAreaType type;//类型

    private StagingAreaCategory category;//类别

    private boolean available;//是否可用

    @ManyToMany
    @JoinTable(
            foreignKey = @ForeignKey(name = "fk_sga_owners_sga"),
            inverseForeignKey = @ForeignKey(name = "fk_sga_owners_own")
    )
    private Set<Owner> owners;//业主

    private BusinessType businessType;//业务类型

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_sga_direction"))
    private Direction direction;//方向

    private TakeMode takeMode;//提货方式
}
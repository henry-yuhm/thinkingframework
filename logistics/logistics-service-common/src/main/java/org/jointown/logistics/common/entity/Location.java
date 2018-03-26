package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Location {
    @Id
    @TableGenerator(name = "LocationId", table = "LocationId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "LocationId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_location_warehouse"))
    private Warehouse warehouse;//仓库

    private String no;//编号

    private String floor;//楼层

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_loc_ara"))
    private Area area;//区域

    private String group;//分组

    private String roadway;//巷道

    private String row;//排

    private String layer;//层

    private String column;//列

    private String shortno;//短编号

    private BigDecimal length;//长

    private BigDecimal width;//宽

    private BigDecimal height;//高

    private BigDecimal volume;//体积

    private BigDecimal occupiedVolume;//占用体积

    private PackageSign packageSign;//包装标识

    private boolean available;//是否可用

    private boolean locking;//是否锁定

    private boolean automatic;//是否自动化

    private LocationType locationType;//货位类型

    private RackMode rackMode;//货架模式

    private StorageType storageType;//存放类型

    private StorageStatus storageStatus;//存放状态

    private PileupType pileupType;//码放类型

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_loc_tfl"))
    private Transferline transferline;//输送线
}
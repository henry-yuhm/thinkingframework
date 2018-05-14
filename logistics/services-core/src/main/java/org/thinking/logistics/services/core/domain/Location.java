package org.thinking.logistics.services.core.domain;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String floor;//楼层

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Area area;//区域

    private String region;//大区

    private String roadway;//巷道

    private String x = "";//排

    private String y = "";//列

    private String z = "";//层

    private String shortno = x + y + z;//短编号

    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal length;//长

    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal width;//宽

    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal height;//高

    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal volume;//体积

    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal occupationVolume;//占用体积

    @Column(nullable = false)
    private PackageType packageType;//包装类型

    @Column(nullable = false)
    private boolean available = true;//可用

    @Column(nullable = false)
    private boolean locking = false;//锁定

    @Column(nullable = false)
    private boolean automatic;//自动化

    @Column(nullable = false)
    private LocationType type = LocationType.NORMAL;//货位类型

    @Column(nullable = false)
    private RackType rackType = RackType.CLAPBOARD;//货架类型

    @Column(nullable = false)
    private StorageType storageType;//存放类型

    @Column(nullable = false)
    private StorageState storageState;//存放状态

    @Column(nullable = false)
    private PileupType pileupType;//码放类型

    @ManyToOne(fetch = FetchType.LAZY)
    private Transferline transferline;//输送线
}
package org.thinking.logistics.services.core.domain.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.support.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Location extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false, updatable = false, length = 30)
    private String no;//编号

    @Column(nullable = false, length = 2)
    private String floor;//楼层

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Area area;//区域

    @Column(length = 2)
    private String region;//大区

    @Column(length = 2)
    private String roadway;//巷道

    @Column(length = 2)
    private String x;//排

    @Column(length = 2)
    private String y;//列

    @Column(length = 2)
    private String z;//层

    @Column(length = 6)
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
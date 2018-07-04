package org.thinking.logistics.services.core.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.support.OperationDevice;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.domain.support.ReviewType;
import org.thinking.logistics.services.core.domain.support.UpshelfMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Area extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false, updatable = false, length = 10)
    private String no;//编号

    @Column(nullable = false, length = 100)
    private String name;//名称

    @Column(nullable = false, length = 20)
    private String storeCategory;//库别

    @Column(nullable = false)
    private String storeNo;//库房编号

    @Column(nullable = false)
    private PackageType packageType;//包装类型

    private String region;//大区

    @Column(nullable = false)
    private OperationDevice operationDevice;//拣货设备

    private UpshelfMode upshelfMode;//上架方式

    @Column(nullable = false)
    private boolean prepicking;//提前拣货

    @Column(nullable = false)
    private boolean useSorter;//使用分拣机

    @Column(nullable = false)
    private ReviewType reviewType;//复核类型

    @Column(nullable = false)
    private int fullloadQuantity = 0;//满载周转箱数

    @Override
    public String toString() {
        return "作业区域【" + this.no + "】";
    }
}
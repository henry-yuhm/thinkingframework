package org.thinking.logistics.services.core.entity;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.OperationDevice;
import org.thinking.logistics.services.core.domain.support.PackageKind;
import org.thinking.logistics.services.core.domain.support.RecheckKind;
import org.thinking.logistics.services.core.domain.support.UpshelfMode;

import javax.persistence.*;

@Entity
@Data
public class Area {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @Column(nullable = false)
    private String name;//名称

    @Column(nullable = false)
    private String storeCategory;//库别

    @Column(nullable = false)
    private String storeNo;//库房编号

    @Column(nullable = false)
    private PackageKind packageKind;//包装类型

    private String region;//大区

    @Column(nullable = false)
    private OperationDevice operationDevice;//拣货设备

    private UpshelfMode upshelfMode;//上架方式

    @Column(nullable = false)
    private boolean prepicking;//提前拣货

    @Column(nullable = false)
    private boolean useSorter;//使用分拣机

    @Column(nullable = false)
    private RecheckKind recheckKind;//复核类型

    @Column(nullable = false)
    private int fullloadQuantity = 0;//满载周转箱数

    @Override
    public String toString() {
        return "作业区域【" + this.number + "】";
    }
}
package org.thinking.sce.service.core.domain.common;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.support.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.LinkedHashMap;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Warehouse extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false, updatable = false, length = 100)
    private String name;//名称

    @Column(nullable = false)
    private boolean electronicLabel;//电子标签

    @Column(nullable = false)
    private boolean pallet;//使用托盘

    @Column(nullable = false)
    private boolean sorter;//有分拣机

    @Column(nullable = false)
    private boolean tablet;//使用平板电脑

    private LinkedHashMap<Integer, TransferlineType> transferlineTypes;//输送线类型

    private LinkedHashMap<Integer, TWFType> twfTypes;//立体库类型

    @Column(length = 200)
    private String address;//地址
}
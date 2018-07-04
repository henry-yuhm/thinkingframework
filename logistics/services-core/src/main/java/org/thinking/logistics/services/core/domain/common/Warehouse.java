package org.thinking.logistics.services.core.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.support.TWFType;
import org.thinking.logistics.services.core.domain.support.TransferlineType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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

    private LinkedHashMap<Integer, TransferlineType> transferlineTypes = new LinkedHashMap<>(16);//输送线类型

    private LinkedHashMap<Integer, TWFType> twfTypes = new LinkedHashMap<>(16);//立体库类型

    @Column(length = 200)
    private String address;//地址
}
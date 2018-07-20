package org.thinking.sce.service.core.domain.common;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.employee.Employee;
import org.thinking.sce.service.core.domain.support.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false, updatable = false, length = 50)
    private String no;//编号

    @Column(nullable = false, length = 100)
    private String sourceCode;//源编码

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer parent;//父客户

    @Column(nullable = false, length = 100)
    private String mnemonicCode;//助记码

    @Column(nullable = false, length = 100)
    private String name;//名称

    @Column(nullable = false, length = 100)
    private String shortName;//简称

    @Column(length = 200)
    private String address;//地址

    @Column(length = 100)
    private String phone;//电话

    @Column(nullable = false)
    private CustomerType type;//类型

    private String identifier;//识别

    private String seat;//所在地

    private LotRequest lotRequest;//批号要求

    private CustomerClassification classification;//分类

    private String grade;//等级

    private String district;//地区

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee businessman;//业务员
}
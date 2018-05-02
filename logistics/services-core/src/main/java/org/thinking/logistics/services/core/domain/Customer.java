package org.thinking.logistics.services.core.domain;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.BatchesRequest;
import org.thinking.logistics.services.core.domain.support.CustomerClassification;
import org.thinking.logistics.services.core.domain.support.CustomerType;

import javax.persistence.*;

@Entity
@Data
//@EqualsAndHashCode(callSuper = true)
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false, length = 50)
    private String no;//编号

    @Column(nullable = false)
    private String sourceCode;//源编码

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer parent;//父客户

    @Column(nullable = false)
    private String mnemonicCode;//助记码

    @Column(nullable = false)
    private String name;//名称

    @Column(nullable = false)
    private String shortName;//简称

    private String address;//地址

    private String phone;//电话

    @Column(nullable = false)
    private CustomerType type;//类型

    private String identifier;//识别

    private String seat;//所在地

    private BatchesRequest batchesRequest;//批号要求

    private CustomerClassification classification;//分类

    private String grade;//等级

    private String district;//地区

    private String businessman;//业务员

//    @Override
//    public void verify(Customer probe) throws Exception {
//        if (!Optional.ofNullable(probe.getOwner()).isPresent()) {
//            throw CompositeException.getException("客户业主不能为空");
//        }
//
//        if (Optional.ofNullable(probe.getNo()).get().isEmpty()) {
//            throw CompositeException.getException("客户编号不能为空");
//        }
//    }
}
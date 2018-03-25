package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.BatchNumberRequest;
import org.jointown.logistics.common.entity.support.CustomerSign;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_customer_owner"))
    private Owner owner;//业主

    private String no;//编号

    private String sourceCode;//源编码

    private Customer parentCustomer;//父客户

    private String mnemonicCode;//助记码

    private String name;//名称

    private String shortName;//简称

    private String address;//地址

    private String phone;//电话

    private String warehouseAddress;//仓库地址

    private CustomerSign sign;//标识

    private String identifier;//识别

    private String location;//位置

    private BatchNumberRequest batchNumberRequest;//批号要求

    private String category;//类别

    private String grade;//等级

    private String district;//地区

    private String businessman;//业务员
}
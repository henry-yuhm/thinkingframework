package org.thinking.logistics.core.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(schema = "wms")
@Data
public class Owner {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, length = 50)
    private String number;//编号

    @Column(unique = true, nullable = false, length = 100)
    private String name;//名称

    private String mnemonicCode;//助记码

    @Column(nullable = false)
    private String inventoryUpper;//库存上限

    private String serviceHotline;//服务热线

    @Column(nullable = false)
    private boolean thirdpart;//是否第三方
}
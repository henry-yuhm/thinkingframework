package org.thinking.logistics.services.core.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Owner {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, length = 50)
    private String no;//编号

    @Column(unique = true, nullable = false, length = 100)
    private String name;//名称

    private String mnemonicCode;//助记码

    @Column(nullable = false)
    private String inventoryUpper;//库存上限

    private String serviceHotline;//服务热线

    @Column(nullable = false)
    private boolean thirdpart;//第三方

//    @Override
//    public void verify(Owner probe) throws Exception {
//        if (Optional.ofNullable(probe.getNo()).get().isEmpty()) {
//            throw CompositeException.getException("业主编号不能为空");
//        }
//    }
}
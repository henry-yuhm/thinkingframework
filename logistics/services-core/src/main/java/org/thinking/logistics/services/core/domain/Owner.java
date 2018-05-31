package org.thinking.logistics.services.core.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Owner {
    @Id
    @TableGenerator(name = "ownerIdGen", table = "ownerIdGen", schema = "wms", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ownerIdGen")
    private long id;

    @Column(unique = true, nullable = false, length = 50)
    private String no;//编号

    @Column(unique = true, nullable = false, length = 100)
    private String name;//名称

    private String mnemonicCode;//助记码

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal inventoryUpper = BigDecimal.ZERO;//库存上限

    private String serviceHotline;//服务热线

    @Column(nullable = false)
    private boolean thirdpart;//第三方

    @Override
    public String toString() {
        return "业主【" + this.no + "】【" + this.name + "】";
    }
}
package org.thinking.logistics.services.core.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Owner extends BaseDomainEntity {
    @Column(unique = true, nullable = false, updatable = false, length = 5)
    private String no;//编号

    @Column(unique = true, nullable = false, length = 100)
    private String name;//名称

    @Column(length = 100)
    private String mnemonicCode;//助记码

    @Column(nullable = false, precision = 22, scale = 5)
    @ColumnDefault("0.00000")
    private BigDecimal inventoryUpper;//库存上限

    @Column(length = 100)
    private String serviceHotline;//服务热线

    @Column(nullable = false)
    private boolean thirdpart;//第三方

    @Override
    public String toString() {
        return "业主【" + this.no + "】【" + this.name + "】";
    }
}
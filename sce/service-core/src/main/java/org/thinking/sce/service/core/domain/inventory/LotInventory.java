package org.thinking.sce.service.core.domain.inventory;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.*;
import org.thinking.sce.service.core.domain.support.ValidPeriodType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_lot_inventory", columnNames = {"item_id", "lot_id"}))
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class LotInventory extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;//商品

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lot lot;//批号

    @Column(nullable = false)
    private ValidPeriodType validPeriodType;//效期类型

    @Column(nullable = false)
    private ValidPeriodType mixedType;//效期类型

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal availableInventory;//可用库存

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal palletInventory;//托盘库存

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal caseInventory;//整件库存

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal remainderInventory;//零散库存

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal intransitInventory;//在途库存
}
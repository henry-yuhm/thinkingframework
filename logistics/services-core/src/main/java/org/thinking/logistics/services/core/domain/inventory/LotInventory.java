package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.core.Item;
import org.thinking.logistics.services.core.domain.core.Lot;
import org.thinking.logistics.services.core.domain.support.ValidPeriodType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_lot_inventory", columnNames = {"item_id", "lot_id", "documentType"}))
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
    private ValidPeriodType type;//效期类型

    @Column(nullable = false)
    private ValidPeriodType mixedType;//效期类型

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableInventory;//可用库存

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal palletInventory;//托盘库存

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal wholepiecesInventory;//整件库存

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainderInventory;//零散库存

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal intransitInventory;//在途库存
}
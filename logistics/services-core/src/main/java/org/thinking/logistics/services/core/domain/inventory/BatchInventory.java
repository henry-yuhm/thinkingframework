package org.thinking.logistics.services.core.domain.inventory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.core.Batch;
import org.thinking.logistics.services.core.domain.core.Goods;
import org.thinking.logistics.services.core.domain.support.ValidPeriodType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_batch_inventory", columnNames = {"goods_id", "batch_id", "type"}))
@Data
@EqualsAndHashCode(callSuper = true)
public class BatchInventory extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batch batch;//批号

    @Column(nullable = false)
    private ValidPeriodType type;//效期类型

    @Column(nullable = false)
    private ValidPeriodType mixedType;//效期类型

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableInventory = BigDecimal.ZERO;//可用库存

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal palletInventory = BigDecimal.ZERO;//托盘库存

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal wholepiecesInventory = BigDecimal.ZERO;//整件库存

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainderInventory = BigDecimal.ZERO;//零散库存

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal intransitInventory = BigDecimal.ZERO;//在途库存
}
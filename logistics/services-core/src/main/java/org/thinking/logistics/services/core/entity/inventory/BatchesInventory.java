package org.thinking.logistics.services.core.entity.inventory;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.ValidPeriodType;
import org.thinking.logistics.services.core.entity.Batches;
import org.thinking.logistics.services.core.entity.Goods;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "lmis", uniqueConstraints = @UniqueConstraint(name = "uk_batches_inventory", columnNames = {"goods_id", "batches_id", "type"}))
@Data
public class BatchesInventory {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batches batches;//批号

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
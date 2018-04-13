package org.thinking.logistics.core.entity.bill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.domain.support.InventoryState;
import org.thinking.logistics.core.domain.support.TransferringReason;
import org.thinking.logistics.core.entity.Location;
import org.thinking.logistics.core.entity.container.Pallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class InventoryCheckDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState inventoryState;//库存状态

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    @Column(nullable = false)
    private int pieces = 0;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainder = BigDecimal.ZERO;//余数

    private String auditor;//审核员

    private TransferringReason reason;//移库原因
}
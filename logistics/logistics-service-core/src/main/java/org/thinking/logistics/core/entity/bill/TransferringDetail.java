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
public class TransferringDetail extends Detail {
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private TransferringHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location sourceLocation;//源货位

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location targetLocation;//目标货位

    @Column(nullable = false)
    private InventoryState sourceState;//源库存状态

    @Column(nullable = false)
    private InventoryState targetState;//目标库存状态

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    @Column(nullable = false)
    private int pieces = 0;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainder = BigDecimal.ZERO;//余数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    private TransferringReason reason;//移库原因
}
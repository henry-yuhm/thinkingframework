package org.thinking.sce.service.core.domain.document;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.common.Location;
import org.thinking.sce.service.core.domain.container.Pallet;
import org.thinking.sce.service.core.domain.support.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class TransferOrderDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location sourceLocation;//源货位

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location targetLocation;//目标货位

    @Column(nullable = false)
    private InventoryState sourceInventoryState;//源库存状态

    @Column(nullable = false)
    private InventoryState targetInventoryState;//目标库存状态

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal quantity;//数量

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal cases;//件数

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal remainder;//余数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    private TransferReason reason;//移库原因

    public BigDecimal getCases() {
        return this.getItem().getCases(quantity);
    }

    public BigDecimal getRemainder() {
        return this.getItem().getRemainder(quantity);
    }
}
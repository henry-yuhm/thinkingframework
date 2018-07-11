package org.thinking.logistics.services.core.domain.document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.common.Location;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.support.InventoryState;
import org.thinking.logistics.services.core.domain.support.TransferReason;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class TransferOrderDetail extends Detail {
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private TransferOrderHeader header;//抬头

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
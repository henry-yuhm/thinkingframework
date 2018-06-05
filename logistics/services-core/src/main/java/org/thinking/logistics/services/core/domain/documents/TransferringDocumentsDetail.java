package org.thinking.logistics.services.core.domain.documents;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.core.Location;
import org.thinking.logistics.services.core.domain.support.InventoryState;
import org.thinking.logistics.services.core.domain.support.TransferringReason;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class TransferringDocumentsDetail extends Detail {
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private TransferringDocumentsHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location sourceLocation;//源货位

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location targetLocation;//目标货位

    @Column(nullable = false)
    private InventoryState sourceInventoryState;//源库存状态

    @Column(nullable = false)
    private InventoryState targetInventoryState;//目标库存状态

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal pieces = BigDecimal.ZERO;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal remainder = BigDecimal.ZERO;//余数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    private TransferringReason reason;//移库原因

    public BigDecimal getPieces() {
        return getGoods().getPieces(quantity);
    }

    public BigDecimal getRemainder() {
        return getGoods().getRemainder(quantity);
    }
}
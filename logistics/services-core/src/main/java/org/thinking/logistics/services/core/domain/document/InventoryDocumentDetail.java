package org.thinking.logistics.services.core.domain.document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class InventoryDocumentDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    @Column(nullable = false)
    private InventoryState inventoryState;//库存状态

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal pieces = BigDecimal.ZERO;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal remainder = BigDecimal.ZERO;//余数

    private String approver;//审核员

    private TransferringReason reason;//移库原因

    public BigDecimal getPieces() {
        return this.getItem().getPieces(quantity);
    }

    public BigDecimal getRemainder() {
        return this.getItem().getRemainder(quantity);
    }
}
package org.thinking.logistics.services.core.domain.documents;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.thinking.logistics.services.core.domain.Location;
import org.thinking.logistics.services.core.domain.container.Pallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ReplenishingDocumentsDetail extends Detail {
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private ReplenishingDocumentsHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    private String storeCategory;//库别

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planQuantity = BigDecimal.ZERO;//计划数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal planPieces = BigDecimal.ZERO;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal factPieces = BigDecimal.ZERO;//实际件数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    public BigDecimal getPlanPieces() {
        return getGoods().getPieces(planQuantity);
    }

    public BigDecimal getFactPieces() {
        return getGoods().getPieces(factQuantity);
    }
}
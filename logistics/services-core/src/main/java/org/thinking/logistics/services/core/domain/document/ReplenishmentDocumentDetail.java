package org.thinking.logistics.services.core.domain.document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.common.Location;
import org.thinking.logistics.services.core.domain.container.Pallet;

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
public class ReplenishmentDocumentDetail extends Detail {
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private ReplenishmentDocumentHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    private String storeCategory;//库别

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planQuantity;//计划数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal planPieces;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity;//实际数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal factPieces;//实际件数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    public BigDecimal getPlanPieces() {
        return this.getItem().getPieces(planQuantity);
    }

    public BigDecimal getFactPieces() {
        return this.getItem().getPieces(factQuantity);
    }
}
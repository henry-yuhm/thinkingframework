package org.thinking.logistics.services.core.entity.bill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.entity.Location;
import org.thinking.logistics.services.core.entity.container.Pallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ReplenishingDetail extends Detail {
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private ReplenishingHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    private String storeCategory;//库别

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planQuantity = BigDecimal.ZERO;//计划数量

    @Column(nullable = false)
    private int planPieces = 0;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false)
    private int factPieces = 0;//实际件数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘
}
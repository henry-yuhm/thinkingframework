package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "parentBillDetail", foreignKey = @ForeignKey(name = "fk_obd_parent_bd"))
})
public class OutboundBillDetail extends BillDetail {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obd_location"))
    private Location location;

    private Stock.StockStatus stockStatus;

    private BatchNumber.BatchNumberRequest batchNumberRequest;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plannedQuantity;

    @Column(nullable = false, precision = 12)
    private BigDecimal plannedPieces;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plannedRemainder;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal actualQuantity;

    @Column(nullable = false, precision = 12)
    private BigDecimal actualPieces;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal actualRemainder;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal fullloadQuantity;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainderQuantity;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal stocklessnessQuantity;

    private boolean original;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal price;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal amount;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal taxTicketAmount;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settleupPrice;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settleupAmount;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal flittingCostPrice;
}
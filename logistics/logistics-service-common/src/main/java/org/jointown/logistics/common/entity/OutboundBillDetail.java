package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.BatchNumberRequest;
import org.jointown.logistics.common.entity.support.Quantity;
import org.jointown.logistics.common.entity.support.StockStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "parentBillDetail", foreignKey = @ForeignKey(name = "fk_obd_parent_bd"))
})
public class OutboundBillDetail extends BillDetail {
    @AttributeOverrides(value = {
            @AttributeOverride(name = "quantity", column = @Column(name = "plannedQuantity", nullable = false, precision = 12, scale = 5)),
            @AttributeOverride(name = "pieces", column = @Column(name = "plannedPieces", nullable = false)),
            @AttributeOverride(name = "remainder", column = @Column(name = "plannedRemainder", nullable = false, precision = 12, scale = 5))
    })
    protected Quantity plannedQuantity;//计划数量

    @AttributeOverrides(value = {
            @AttributeOverride(name = "quantity", column = @Column(name = "actualQuantity", nullable = false, precision = 12, scale = 5)),
            @AttributeOverride(name = "pieces", column = @Column(name = "actualPieces", nullable = false)),
            @AttributeOverride(name = "remainder", column = @Column(name = "actualRemainder", nullable = false, precision = 12, scale = 5))
    })
    protected Quantity actualQuantity;//实际数量

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obd_loc"))
    private Location location;//货位

    private StockStatus stockStatus;//库存状态

    private BatchNumberRequest batchNumberRequest;//批号要求

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal wholeQuantity;//整件未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainderQuantity;//零货未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal stocklessnessQuantity;//库存不足数量

    private boolean original;//是否原始数据

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal price;//单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal amount;//金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal taxTicketAmount;//税票金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settleupPrice;//结算单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal settleupAmount;//结算金额

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal flittingCostPrice;//调拨成本价
}
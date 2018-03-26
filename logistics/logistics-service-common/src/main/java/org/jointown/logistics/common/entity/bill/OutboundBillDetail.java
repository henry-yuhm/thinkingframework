package org.jointown.logistics.common.entity.bill;

import org.jointown.logistics.common.entity.Location;
import org.jointown.logistics.common.entity.support.Quantity;
import org.jointown.logistics.common.entity.support.StockStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class OutboundBillDetail extends BillDetail {
    @OneToOne(fetch = FetchType.LAZY)
    private Location location;//货位

    private StockStatus stockStatus;//库存状态

    @AttributeOverrides(value = {
            @AttributeOverride(name = "quantity", column = @Column(name = "plannedQuantity", nullable = false, precision = 12, scale = 5)),
            @AttributeOverride(name = "pieces", column = @Column(name = "plannedPieces", nullable = false)),
            @AttributeOverride(name = "remainder", column = @Column(name = "plannedRemainder", nullable = false, precision = 12, scale = 5))
    })
    private Quantity plannedQuantity;//计划数量

    @AttributeOverrides(value = {
            @AttributeOverride(name = "quantity", column = @Column(name = "actualQuantity", nullable = false, precision = 12, scale = 5)),
            @AttributeOverride(name = "pieces", column = @Column(name = "actualPieces", nullable = false)),
            @AttributeOverride(name = "remainder", column = @Column(name = "actualRemainder", nullable = false, precision = 12, scale = 5))
    })
    private Quantity actualQuantity;//实际数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal wholeQuantity;//整件未处理数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainderQuantity;//零货未处理数量

    private boolean original;//是否原始数据
}
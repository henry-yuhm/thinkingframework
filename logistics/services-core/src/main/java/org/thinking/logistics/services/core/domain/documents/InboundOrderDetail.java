package org.thinking.logistics.services.core.domain.documents;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.thinking.logistics.services.core.domain.container.Pallet;
import org.thinking.logistics.services.core.domain.container.Totebox;
import org.thinking.logistics.services.core.domain.support.Assessment;
import org.thinking.logistics.services.core.domain.support.Conveyance;
import org.thinking.logistics.services.core.domain.support.ReceiptConclusion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class InboundOrderDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY)
    private InboundOrderDetail parent;//父明细

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private InboundOrderHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOrderDetail order;//订单

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planQuantity = BigDecimal.ZERO;//计划数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal planPieces = BigDecimal.ZERO;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal planRemainder = BigDecimal.ZERO;//计划余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal factPieces = BigDecimal.ZERO;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal factRemainder = BigDecimal.ZERO;//实际余数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Totebox totebox;//周转箱

    @Column(nullable = false)
    private Assessment assessment;//验收评定

    @Column(nullable = false)
    private ReceiptConclusion receiptConclusion;//收货结论

    private String rejections;//拒收原因

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal price = BigDecimal.ZERO;//单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal amount = BigDecimal.ZERO;//金额

    @Column(nullable = false)
    private String storeNo;//库房编号

    private String splitter;//拆分人

    private int temperature;//温度

    private Conveyance conveyance;//运输工具

    private String scanningNo;//扫描单号

    public BigDecimal getPlanPieces() {
        return getGoods().getPieces(planQuantity);
    }

    public BigDecimal getPlanRemainder() {
        return getGoods().getRemainder(planQuantity);
    }

    public BigDecimal getFactPieces() {
        return getGoods().getPieces(factQuantity);
    }

    public BigDecimal getFactRemainder() {
        return getGoods().getRemainder(factQuantity);
    }
}
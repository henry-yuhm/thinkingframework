package org.thinking.logistics.services.core.domain.document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ReceivingNoteDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY)
    private ReceivingNoteDetail parent;//父明细

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private ReceivingNoteHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOrderDetail order;//订单

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal expectedQuantity;//计划数量

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal expectedCases;//计划件数

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal expectedRemainder;//计划余数

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal actualQuantity;//实际数量

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal actualCases;//实际件数

    @Column(nullable = false, precision = 22, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal actualRemainder;//实际余数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Totebox totebox;//周转箱

    @Column(nullable = false)
    private Assessment assessment;//验收评定

    @Column(nullable = false)
    private ReceiptConclusion receiptConclusion;//收货结论

    private String rejections;//拒收原因

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal price;//单价

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal amount;//金额

    @Column(nullable = false)
    private String storeNo;//库房编号

    private String splitter;//拆分人

    private int temperature;//温度

    private Conveyance conveyance;//运输工具

    private String scanningNo;//扫描单号

    public BigDecimal getExpectedCases() {
        return this.getItem().getCases(expectedQuantity);
    }

    public BigDecimal getExpectedRemainder() {
        return this.getItem().getRemainder(expectedQuantity);
    }

    public BigDecimal getActualCases() {
        return this.getItem().getCases(actualQuantity);
    }

    public BigDecimal getActualRemainder() {
        return this.getItem().getRemainder(actualQuantity);
    }
}
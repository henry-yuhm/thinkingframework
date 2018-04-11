package org.thinking.logistics.core.entity.bill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.domain.support.Assessment;
import org.thinking.logistics.core.domain.support.Conveyance;
import org.thinking.logistics.core.domain.support.ReceiptConclusion;
import org.thinking.logistics.core.entity.container.Pallet;
import org.thinking.logistics.core.entity.container.Totebox;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class InboundDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY)
    private InboundDetail parent;//父明细

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private InboundHeader header;//抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOrderDetail order;//订单

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planQuantity = BigDecimal.ZERO;//计划数量

    @Column(nullable = false)
    private int planPieces = 0;//计划件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planRemainder = BigDecimal.ZERO;//计划余数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false)
    private int factPieces = 0;//实际件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factRemainder = BigDecimal.ZERO;//实际余数

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Totebox totebox;//周转箱

    @Column(nullable = false)
    private Assessment assessment;//验收评定

    @Column(nullable = false)
    private ReceiptConclusion conclusion;//收货结论

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
}
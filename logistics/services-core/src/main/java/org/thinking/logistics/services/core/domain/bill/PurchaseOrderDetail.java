package org.thinking.logistics.services.core.domain.bill;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrderDetail extends Detail {
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private PurchaseOrderHeader header;//抬头

    @Column(nullable = false)
    private boolean complete = false;//完成

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planQuantity = BigDecimal.ZERO;//计划数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal price = BigDecimal.ZERO;//单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal amount = BigDecimal.ZERO;//金额
}
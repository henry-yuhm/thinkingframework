package org.thinking.logistics.services.core.domain.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrderDetail extends Detail {
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private PurchaseOrderHeader header;//抬头

    @Column(nullable = false)
    private boolean completed;//完成

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal expectedQuantity;//计划数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal actualQuantity;//实际数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal price;//单价

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal amount;//金额
}
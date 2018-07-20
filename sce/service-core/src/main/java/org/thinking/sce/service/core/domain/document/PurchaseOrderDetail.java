package org.thinking.sce.service.core.domain.document;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrderDetail extends Detail {
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
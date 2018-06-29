package org.thinking.logistics.services.core.domain.document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.ReversionStage;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ReversionNoteDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ShipmentOrderHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ShipmentOrderDetail detail;//单据明细

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity;//数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal pieces;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal remainder;//余数

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Employee operator;//操作员

    @Column(nullable = false)
    private ReversionStage stage;//冲红阶段

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee auditor;//审核员

    @Temporal(TemporalType.DATE)
    private Instant auditTime;//审核时间

    private String reasons;//原因

    public BigDecimal getPieces() {
        return this.getItem().getPieces(quantity);
    }

    public BigDecimal getRemainder() {
        return this.getItem().getRemainder(quantity);
    }
}
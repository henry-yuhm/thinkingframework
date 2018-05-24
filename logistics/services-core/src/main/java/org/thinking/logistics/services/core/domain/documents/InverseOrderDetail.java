package org.thinking.logistics.services.core.domain.documents;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.InverseStage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class InverseOrderDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundOrderHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundOrderDetail detail;//单据明细

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal pieces = BigDecimal.ZERO;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    @Setter(value = AccessLevel.NONE)
    private BigDecimal remainder = BigDecimal.ZERO;//余数

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Employee operator;//操作员

    @Column(nullable = false)
    private Date operationTime = Date.valueOf(LocalDate.now());//操作时间

    @Column(nullable = false)
    private InverseStage stage;//冲红阶段

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee auditor;//审核员

    private Date auditTime;//审核时间

    private String reasons;//原因

    public BigDecimal getPieces() {
        return getGoods().getPieces(quantity);
    }

    public BigDecimal getRemainder() {
        return getGoods().getRemainder(quantity);
    }
}
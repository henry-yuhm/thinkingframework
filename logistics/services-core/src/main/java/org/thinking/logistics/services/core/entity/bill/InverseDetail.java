package org.thinking.logistics.services.core.entity.bill;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class InverseDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundDetail detail;//单据明细

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    @Column(nullable = false)
    private BigDecimal pieces = BigDecimal.ZERO;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainder = BigDecimal.ZERO;//余数

    @Column(nullable = false)
    private String operator;//操作员

    @Column(nullable = false)
    private Date operationTime = Date.valueOf(LocalDate.now());//操作时间

    @Column(nullable = false)
    private InverseStage stage;//冲红阶段

    private String auditor;//审核员

    private Date auditTime;//审核时间

    private String reasons;//原因
}
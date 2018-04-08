package org.thinking.logistics.core.entity.bill;

import org.thinking.logistics.core.domain.support.InverseStage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class InverseDetail extends Detail {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutboundDetail detail;//单据明细

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    @Column(nullable = false)
    private int pieces = 0;//件数

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal remainder = BigDecimal.ZERO;//余数

    @Column(nullable = false)
    private String operator;//操作员

    @Column(nullable = false)
    private Date operationTime = Date.valueOf(LocalDate.now());//操作时间

    @Column(nullable = false)
    private InverseStage kind;//冲红类型

    private String auditor;//审核员

    private Date auditTime;//审核时间

    private String reasons;//原因

    public InverseDetail() {
    }

    public OutboundHeader getHeader() {
        return header;
    }

    public void setHeader(OutboundHeader header) {
        this.header = header;
    }

    public OutboundDetail getDetail() {
        return detail;
    }

    public void setDetail(OutboundDetail detail) {
        this.detail = detail;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public BigDecimal getRemainder() {
        return remainder;
    }

    public void setRemainder(BigDecimal remainder) {
        this.remainder = remainder;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public InverseStage getKind() {
        return kind;
    }

    public void setKind(InverseStage kind) {
        this.kind = kind;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }
}
package org.thinking.logistics.core.entity.bill;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PurchaseOrderDetail extends Detail {
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private PurchaseOrderHeader header;//抬头

    @Column(nullable = false)
    private boolean complete = false;//是否完成

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal planQuantity = BigDecimal.ZERO;//计划数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal factQuantity = BigDecimal.ZERO;//实际数量

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal price = BigDecimal.ZERO;//单价

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal amount = BigDecimal.ZERO;//金额

    public PurchaseOrderDetail() {
    }

//    @Override
//    public PurchaseOrderHeader getHeader() {
//        return header;
//    }
//
//    public void setHeader(PurchaseOrderHeader header) {
//        this.header = header;
//    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public BigDecimal getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(BigDecimal planQuantity) {
        this.planQuantity = planQuantity;
    }

    public BigDecimal getFactQuantity() {
        return factQuantity;
    }

    public void setFactQuantity(BigDecimal factQuantity) {
        this.factQuantity = factQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
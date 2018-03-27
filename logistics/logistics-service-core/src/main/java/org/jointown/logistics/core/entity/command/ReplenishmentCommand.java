package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.bill.ReplenishmentBillDetail;
import org.jointown.logistics.core.entity.bill.ReplenishmentBillHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class ReplenishmentCommand extends TransitionCommand {
    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private ReplenishmentBillHeader header;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private ReplenishmentBillDetail detail;//单据明细

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableQuantity;//可用数量

    public ReplenishmentCommand() {
    }

    public ReplenishmentBillHeader getHeader() {
        return header;
    }

    public void setHeader(ReplenishmentBillHeader header) {
        this.header = header;
    }

    public ReplenishmentBillDetail getDetail() {
        return detail;
    }

    public void setDetail(ReplenishmentBillDetail detail) {
        this.detail = detail;
    }

    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
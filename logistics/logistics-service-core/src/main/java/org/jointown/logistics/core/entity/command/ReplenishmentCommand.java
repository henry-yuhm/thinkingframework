package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.bill.ReplenishmentDetail;
import org.jointown.logistics.core.entity.bill.ReplenishmentHeader;
import org.jointown.logistics.core.entity.container.Pallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class ReplenishmentCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReplenishmentHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReplenishmentDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableQuantity = BigDecimal.ZERO;//可用数量

    public ReplenishmentCommand() {
    }

    public ReplenishmentHeader getHeader() {
        return header;
    }

    public void setHeader(ReplenishmentHeader header) {
        this.header = header;
    }

    public ReplenishmentDetail getDetail() {
        return detail;
    }

    public void setDetail(ReplenishmentDetail detail) {
        this.detail = detail;
    }

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
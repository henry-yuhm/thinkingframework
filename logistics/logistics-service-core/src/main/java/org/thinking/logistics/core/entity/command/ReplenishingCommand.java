package org.thinking.logistics.core.entity.command;

import org.thinking.logistics.core.entity.bill.ReplenishingDetail;
import org.thinking.logistics.core.entity.bill.ReplenishingHeader;
import org.thinking.logistics.core.entity.container.Pallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class ReplenishingCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReplenishingHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReplenishingDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableQuantity = BigDecimal.ZERO;//可用数量

    public ReplenishingCommand() {
    }

    public ReplenishingHeader getHeader() {
        return header;
    }

    public void setHeader(ReplenishingHeader header) {
        this.header = header;
    }

    public ReplenishingDetail getDetail() {
        return detail;
    }

    public void setDetail(ReplenishingDetail detail) {
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
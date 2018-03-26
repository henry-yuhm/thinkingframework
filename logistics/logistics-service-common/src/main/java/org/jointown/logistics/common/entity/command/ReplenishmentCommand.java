package org.jointown.logistics.common.entity.command;

import org.jointown.logistics.common.entity.bill.ReplenishmentBillDetail;
import org.jointown.logistics.common.entity.bill.ReplenishmentBillHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class ReplenishmentCommand extends TransitionCommand {
    @OneToOne(fetch = FetchType.LAZY)
    private ReplenishmentBillHeader billHeader;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    private ReplenishmentBillDetail billDetail;//单据明细

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableQuantity = BigDecimal.ZERO;//可用数量
}
package org.thinking.logistics.services.core.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.Batches;
import org.thinking.logistics.services.core.domain.bill.ReplenishingDetail;
import org.thinking.logistics.services.core.domain.bill.ReplenishingHeader;
import org.thinking.logistics.services.core.domain.container.Pallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ReplenishingCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReplenishingHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReplenishingDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Batches batches;//批号

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableQuantity = BigDecimal.ZERO;//可用数量
}
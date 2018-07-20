package org.thinking.sce.service.core.domain.command;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.common.Lot;
import org.thinking.sce.service.core.domain.container.Pallet;
import org.thinking.sce.service.core.domain.document.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ReplenishmentCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReplenishmentOrderHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReplenishmentOrderDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lot lot;//批号

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal availableQuantity;//可用数量
}
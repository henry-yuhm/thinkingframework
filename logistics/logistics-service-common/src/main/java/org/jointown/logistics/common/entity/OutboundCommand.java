package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class OutboundCommand extends Command {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obc_location"))
    private Location location;

    private Stock.StockStatus stockStatus;

    private boolean active;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creativeQuantity;

    @Column(nullable = false, precision = 12)
    private BigDecimal creativePieces;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creativeRemainder;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plannedQuantity;

    @Column(nullable = false, precision = 12)
    private BigDecimal plannedPieces;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plannedRemainder;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal actualQuantity;

    @Column(nullable = false, precision = 12)
    private BigDecimal actualPieces;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal actualRemainder;

    @ManyToMany
    private Set<TransferCommand> transferCommands;
}
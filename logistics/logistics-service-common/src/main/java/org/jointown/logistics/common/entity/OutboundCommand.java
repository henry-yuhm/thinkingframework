package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
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

    @Column(nullable = false)
    private BigInteger creativePieces = BigInteger.ZERO;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal creativeRemainder;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plannedQuantity;

    @Column(nullable = false)
    private BigInteger plannedPieces = BigInteger.ZERO;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal plannedRemainder;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal actualQuantity;

    @Column(nullable = false)
    private BigInteger actualPieces = BigInteger.ZERO;

    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal actualRemainder;

    private String splitBillNo;

    private AppendixSign appendixSign;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obc_tbx"))
    private Totebox totebox;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obc_rct"))
    private RecheckTable recheckTable;

    private String pickingOrder;

    private BigInteger taskGroup;

    private String bufferNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obc_pfm"))
    private Platform platform;

    private BigDecimal tcmRemainder;

    @ManyToMany
    private Set<TransferCommand> transferCommands;
}
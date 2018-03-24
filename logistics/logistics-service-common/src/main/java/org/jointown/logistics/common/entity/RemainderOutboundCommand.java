package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
public class RemainderOutboundCommand extends OutboundCommand {
    private String splitBillNo;//拆分单号

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obc_tbx"))
    private Totebox totebox;//周转箱

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obc_rct"))
    private RecheckTable recheckTable;//复核台

    private BigInteger taskGroup;//任务分组

    private String bufferNo;//暂存位编号

    private BigDecimal remainder;//余量
}
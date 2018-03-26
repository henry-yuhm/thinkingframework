package org.jointown.logistics.common.entity.command;

import org.jointown.logistics.common.entity.RecheckTable;
import org.jointown.logistics.common.entity.barcode.RemainderOutboundBarcode;
import org.jointown.logistics.common.entity.container.Totebox;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
public class RemainderCommand extends OutboundCommand {
    @OneToOne(fetch = FetchType.LAZY)
    private RemainderCommand parent;//父指令

    @OneToOne(fetch = FetchType.LAZY)
    private RemainderOutboundBarcode barcode;//作业条码

    private String splitBillNo;//拆分单号

    @OneToOne(fetch = FetchType.LAZY)
    private Totebox totebox;//周转箱

    @OneToOne(fetch = FetchType.LAZY)
    private RecheckTable recheckTable;//复核台

    private BigInteger taskGroup;//任务分组

    private String bufferNo;//暂存位编号

    private BigDecimal remainder;//余量
}
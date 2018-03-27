package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.barcode.RemainderBarcode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class RemainderCommand extends OutboundCommand {
    @OneToOne(fetch = FetchType.LAZY)
    private RemainderCommand parent;//父指令

    @OneToOne(fetch = FetchType.LAZY)
    private RemainderBarcode barcode;//作业条码

    private BigDecimal remainder;//余量

    public RemainderCommand() {
    }

    public RemainderCommand getParent() {
        return parent;
    }

    public void setParent(RemainderCommand parent) {
        this.parent = parent;
    }

    public RemainderBarcode getBarcode() {
        return barcode;
    }

    public void setBarcode(RemainderBarcode barcode) {
        this.barcode = barcode;
    }

    public BigDecimal getRemainder() {
        return remainder;
    }

    public void setRemainder(BigDecimal remainder) {
        this.remainder = remainder;
    }
}
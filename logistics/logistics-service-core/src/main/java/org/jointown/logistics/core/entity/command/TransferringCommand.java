package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.bill.TransferringDetail;
import org.jointown.logistics.core.entity.bill.TransferringHeader;
import org.jointown.logistics.core.entity.container.Pallet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class TransferringCommand extends TransitionCommand {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TransferringHeader header;//单据抬头

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TransferringDetail detail;//单据明细

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    public TransferringCommand() {
    }

    public TransferringHeader getHeader() {
        return header;
    }

    public void setHeader(TransferringHeader header) {
        this.header = header;
    }

    public TransferringDetail getDetail() {
        return detail;
    }

    public void setDetail(TransferringDetail detail) {
        this.detail = detail;
    }

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }
}
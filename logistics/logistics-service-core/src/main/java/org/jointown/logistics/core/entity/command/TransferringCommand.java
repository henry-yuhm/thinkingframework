package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.bill.TransferringBillDetail;
import org.jointown.logistics.core.entity.bill.TransferringBillHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class TransferringCommand extends TransitionCommand {
    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private TransferringBillHeader header;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private TransferringBillDetail detail;//单据明细

    public TransferringCommand() {
    }

    public TransferringBillHeader getHeader() {
        return header;
    }

    public void setHeader(TransferringBillHeader header) {
        this.header = header;
    }

    public TransferringBillDetail getDetail() {
        return detail;
    }

    public void setDetail(TransferringBillDetail detail) {
        this.detail = detail;
    }
}
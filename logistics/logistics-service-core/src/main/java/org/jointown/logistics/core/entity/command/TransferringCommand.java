package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.bill.TransferringDetail;
import org.jointown.logistics.core.entity.bill.TransferringHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class TransferringCommand extends TransitionCommand {
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private TransferringHeader header;//单据抬头

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private TransferringDetail detail;//单据明细

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
}
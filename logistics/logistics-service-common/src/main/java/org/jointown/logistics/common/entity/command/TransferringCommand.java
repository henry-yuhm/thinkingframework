package org.jointown.logistics.common.entity.command;

import org.jointown.logistics.common.entity.bill.TransferringBillDetail;
import org.jointown.logistics.common.entity.bill.TransferringBillHeader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class TransferringCommand extends TransitionCommand {
    @OneToOne(fetch = FetchType.LAZY)
    private TransferringBillHeader billHeader;//单据抬头

    @OneToOne(fetch = FetchType.LAZY)
    private TransferringBillDetail billDetail;//单据明细
}
package org.jointown.logistics.common.entity.command;

import org.jointown.logistics.common.entity.Platform;
import org.jointown.logistics.common.entity.barcode.WholeOutboundBarcode;
import org.jointown.logistics.common.entity.container.Pallet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class WholeCommand extends OutboundCommand {
    @OneToOne(fetch = FetchType.LAZY)
    private WholeCommand parent;//父指令

    @OneToOne(fetch = FetchType.LAZY)
    private WholeOutboundBarcode barcode;//作业条码

    @OneToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @OneToOne(fetch = FetchType.LAZY)
    private Platform platform;//站台
}
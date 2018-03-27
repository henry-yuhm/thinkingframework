package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.Platform;
import org.jointown.logistics.core.entity.barcode.WholeBarcode;
import org.jointown.logistics.core.entity.container.Pallet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class WholeCommand extends OutboundCommand {
    @OneToOne(fetch = FetchType.LAZY)
    private WholeCommand parent;//父指令

    @OneToOne(fetch = FetchType.LAZY)
    private WholeBarcode barcode;//作业条码

    @OneToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @OneToOne(fetch = FetchType.LAZY)
    private Platform platform;//站台

    public WholeCommand() {
    }

    public WholeCommand getParent() {
        return parent;
    }

    public void setParent(WholeCommand parent) {
        this.parent = parent;
    }

    public WholeBarcode getBarcode() {
        return barcode;
    }

    public void setBarcode(WholeBarcode barcode) {
        this.barcode = barcode;
    }

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
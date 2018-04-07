package org.thinking.logistics.core.entity.command;

import org.thinking.logistics.core.entity.Platform;
import org.thinking.logistics.core.entity.barcode.WholeBarcode;
import org.thinking.logistics.core.entity.container.Pallet;
import org.thinking.logistics.core.entity.task.WholeTask;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class WholeCommand extends OutboundCommand {
    @ManyToOne(fetch = FetchType.LAZY)
    private WholeCommand parent;//父指令

    @ManyToOne(fetch = FetchType.LAZY)
    private WholeTask task;//作业任务

    @ManyToOne(fetch = FetchType.LAZY)
    private WholeBarcode barcode;//作业条码

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Platform platform;//站台

    public WholeCommand() {
    }

    public WholeCommand getParent() {
        return parent;
    }

    public void setParent(WholeCommand parent) {
        this.parent = parent;
    }

    public WholeTask getTask() {
        return task;
    }

    public void setTask(WholeTask task) {
        this.task = task;
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
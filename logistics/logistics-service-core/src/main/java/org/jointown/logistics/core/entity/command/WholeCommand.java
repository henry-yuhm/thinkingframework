package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.Platform;
import org.jointown.logistics.core.entity.barcode.WholeBarcode;
import org.jointown.logistics.core.entity.container.Pallet;
import org.jointown.logistics.core.entity.task.WholeTask;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "command_id"), inverseJoinColumns = @JoinColumn(name = "rep_command_id"))
    private Set<ReplenishmentCommand> commands = new LinkedHashSet<>();//补货指令

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

    @Override
    public Set<ReplenishmentCommand> getCommands() {
        return commands;
    }

    @Override
    public void setCommands(Set<ReplenishmentCommand> commands) {
        this.commands = commands;
    }
}
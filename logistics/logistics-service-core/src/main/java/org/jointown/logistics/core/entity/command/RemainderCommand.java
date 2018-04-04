package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.barcode.RemainderBarcode;
import org.jointown.logistics.core.entity.task.RemainderTask;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class RemainderCommand extends OutboundCommand {
    @ManyToOne(fetch = FetchType.LAZY)
    private RemainderCommand parent;//父指令

    @ManyToOne(fetch = FetchType.LAZY)
    private RemainderTask task;//作业任务

    @ManyToOne(fetch = FetchType.LAZY)
    private RemainderBarcode barcode;//作业条码

    private BigDecimal remainder = BigDecimal.ZERO;//余量

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "command_id"), inverseJoinColumns = @JoinColumn(name = "rep_command_id"))
    private Set<ReplenishmentCommand> commands = new LinkedHashSet<>();//补货指令

    public RemainderCommand() {
    }

    public RemainderCommand getParent() {
        return parent;
    }

    public void setParent(RemainderCommand parent) {
        this.parent = parent;
    }

    public RemainderTask getTask() {
        return task;
    }

    public void setTask(RemainderTask task) {
        this.task = task;
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

    @Override
    public Set<ReplenishmentCommand> getCommands() {
        return commands;
    }

    @Override
    public void setCommands(Set<ReplenishmentCommand> commands) {
        this.commands = commands;
    }
}
package org.thinking.logistics.core.entity.barcode;

import org.thinking.logistics.core.entity.command.ReplenishingCommand;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class ReplenishingBarcode extends TaskBarcode {
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "barcode_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<ReplenishingCommand> commands = new LinkedHashSet<>();//指令

    public ReplenishingBarcode() {
    }

    public Set<ReplenishingCommand> getCommands() {
        return commands;
    }

    public void setCommands(Set<ReplenishingCommand> commands) {
        this.commands = commands;
    }
}
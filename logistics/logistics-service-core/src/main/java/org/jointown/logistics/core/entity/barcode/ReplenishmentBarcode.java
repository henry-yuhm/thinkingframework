package org.jointown.logistics.core.entity.barcode;

import org.jointown.logistics.core.entity.command.ReplenishmentCommand;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class ReplenishmentBarcode extends TaskBarcode {
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "barcode_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<ReplenishmentCommand> commands = new LinkedHashSet<>();//指令

    public ReplenishmentBarcode() {
    }

    public Set<ReplenishmentCommand> getCommands() {
        return commands;
    }

    public void setCommands(Set<ReplenishmentCommand> commands) {
        this.commands = commands;
    }
}
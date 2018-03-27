package org.jointown.logistics.core.entity.barcode;

import org.jointown.logistics.core.entity.command.ReplenishmentCommand;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class ReplenishmentBarcode extends TaskBarcode {
    @ManyToMany
    private Set<ReplenishmentCommand> commands;//指令

    public Set<ReplenishmentCommand> getCommands() {
        return commands;
    }

    public void setCommands(Set<ReplenishmentCommand> commands) {
        this.commands = commands;
    }
}
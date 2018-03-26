package org.jointown.logistics.common.entity.barcode;

import org.jointown.logistics.common.entity.command.TransitionCommand;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class ReplenishmentBarcode extends OperationBarcode {
    @ManyToMany
    private Set<TransitionCommand> commands;//指令

    public Set<TransitionCommand> getCommands() {
        return commands;
    }

    public void setCommands(Set<TransitionCommand> commands) {
        this.commands = commands;
    }
}
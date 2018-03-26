package org.jointown.logistics.common.entity.barcode;

import org.jointown.logistics.common.entity.command.WholeCommand;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class WholeOutboundBarcode extends OutboundBarcode {
    @ManyToMany
    private Set<WholeCommand> commands;//指令

    public Set<WholeCommand> getCommands() {
        return commands;
    }

    public void setCommands(Set<WholeCommand> commands) {
        this.commands = commands;
    }
}
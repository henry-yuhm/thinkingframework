package org.thinking.logistics.core.entity.barcode;

import org.thinking.logistics.core.entity.command.WholeCommand;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class WholeBarcode extends OutboundBarcode {
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "barcode_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<WholeCommand> commands = new LinkedHashSet<>();//指令

    public WholeBarcode() {
    }

    public Set<WholeCommand> getCommands() {
        return commands;
    }

    public void setCommands(Set<WholeCommand> commands) {
        this.commands = commands;
    }
}
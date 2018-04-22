package org.thinking.logistics.services.core.entity.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.entity.command.WholepiecesCommand;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class WholepiecesBarcode extends OutboundBarcode {
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "barcode_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<WholepiecesCommand> commands = new LinkedHashSet<>();//指令
}
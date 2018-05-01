package org.thinking.logistics.services.core.entity.scheduler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.entity.command.OutboundCommand;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InventoryScheduler extends Scheduler {
    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "scheduler_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<OutboundCommand> commands = new LinkedHashSet<>();//指令
}
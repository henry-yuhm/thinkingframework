package org.thinking.sce.service.core.domain.scheduler;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.command.ShipmentCommand;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InventoryScheduler extends Scheduler {
    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "scheduler_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<ShipmentCommand> commands;//指令
}
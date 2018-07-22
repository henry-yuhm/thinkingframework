package org.thinking.sce.service.core.domain.scheduler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.command.ShipmentCommand;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
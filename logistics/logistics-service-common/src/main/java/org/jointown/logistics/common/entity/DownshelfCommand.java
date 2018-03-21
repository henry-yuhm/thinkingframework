package org.jointown.logistics.common.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class DownshelfCommand extends OperationCommand {
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<ReplenishmentCommand> replenishmentCommands;
}
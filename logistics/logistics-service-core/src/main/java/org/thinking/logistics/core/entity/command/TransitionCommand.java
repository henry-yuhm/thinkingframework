package org.thinking.logistics.core.entity.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.domain.support.InventoryState;
import org.thinking.logistics.core.entity.Location;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TransitionCommand extends Command {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location sourceLocation;//源货位

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location targetLocation;//目标货位

    @Column(nullable = false)
    private InventoryState sourceState;//源库存状态

    @Column(nullable = false)
    private InventoryState targetState;//目标库存状态

    @Column(nullable = false)
    private boolean sourceActivated = false;//源是否激活

    @Column(nullable = false)
    private boolean targetActivated = false;//目标是否激活
}
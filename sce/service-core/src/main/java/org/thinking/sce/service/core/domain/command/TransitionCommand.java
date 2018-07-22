package org.thinking.sce.service.core.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.common.Location;
import org.thinking.sce.service.core.domain.support.InventoryState;

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
    private InventoryState sourceInventoryState;//源库存状态

    @Column(nullable = false)
    private InventoryState targetInventoryState;//目标库存状态

    @Column(nullable = false)
    private boolean sourceActivated = false;//源激活

    @Column(nullable = false)
    private boolean targetActivated = false;//目标激活
}
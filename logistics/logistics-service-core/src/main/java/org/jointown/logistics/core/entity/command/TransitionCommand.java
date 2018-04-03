package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.Location;
import org.jointown.logistics.core.entity.support.StockState;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TransitionCommand extends Command {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location sourceLocation;//源货位

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location targetLocation;//目标货位

    @Column(nullable = false)
    private StockState sourceState;//源库存状态

    @Column(nullable = false)
    private StockState targetState;//目标库存状态

    @Column(nullable = false)
    private boolean sourceActive;//源是否激活

    @Column(nullable = false)
    private boolean targetActive;//目标是否激活

    public Location getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(Location sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public Location getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    public StockState getSourceState() {
        return sourceState;
    }

    public void setSourceState(StockState sourceState) {
        this.sourceState = sourceState;
    }

    public StockState getTargetState() {
        return targetState;
    }

    public void setTargetState(StockState targetState) {
        this.targetState = targetState;
    }

    public boolean isSourceActive() {
        return sourceActive;
    }

    public void setSourceActive(boolean sourceActive) {
        this.sourceActive = sourceActive;
    }

    public boolean isTargetActive() {
        return targetActive;
    }

    public void setTargetActive(boolean targetActive) {
        this.targetActive = targetActive;
    }
}
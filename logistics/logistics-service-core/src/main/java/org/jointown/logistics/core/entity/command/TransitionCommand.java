package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.Location;
import org.jointown.logistics.core.entity.support.StockStatus;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class TransitionCommand extends Command {
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Location sourceLocation;//源货位

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Location targetLocation;//目标货位

    @Column(nullable = false)
    private StockStatus sourceStockStatus;//源库存状态

    @Column(nullable = false)
    private StockStatus targetStockStatus;//目标库存状态

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

    public StockStatus getSourceStockStatus() {
        return sourceStockStatus;
    }

    public void setSourceStockStatus(StockStatus sourceStockStatus) {
        this.sourceStockStatus = sourceStockStatus;
    }

    public StockStatus getTargetStockStatus() {
        return targetStockStatus;
    }

    public void setTargetStockStatus(StockStatus targetStockStatus) {
        this.targetStockStatus = targetStockStatus;
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
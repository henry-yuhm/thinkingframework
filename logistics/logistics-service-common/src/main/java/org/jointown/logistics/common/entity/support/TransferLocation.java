package org.jointown.logistics.common.entity.support;

import org.jointown.logistics.common.entity.Location;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Embeddable
public class TransferLocation {
    @OneToOne(fetch = FetchType.LAZY)
    private Location sourceLocation;

    @OneToOne(fetch = FetchType.LAZY)
    private Location targetLocation;

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
}
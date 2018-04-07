package org.thinking.logistics.core.entity.bill;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class ReplenishingHeader extends Header {
    @Column(nullable = false)
    private boolean complete = false;//是否完成

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<ReplenishingDetail> details = new LinkedHashSet<>();//单据明细

    public ReplenishingHeader() {
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public Set<ReplenishingDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<ReplenishingDetail> details) {
        this.details = details;
    }
}
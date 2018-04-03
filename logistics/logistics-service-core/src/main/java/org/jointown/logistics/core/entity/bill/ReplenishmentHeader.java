package org.jointown.logistics.core.entity.bill;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class ReplenishmentHeader extends Header {
    @Column(nullable = false)
    private boolean complete;//是否完成

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<ReplenishmentDetail> details = new LinkedHashSet<>();//单据明细

    public ReplenishmentHeader() {
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public Set<ReplenishmentDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<ReplenishmentDetail> details) {
        this.details = details;
    }
}
package org.jointown.logistics.core.entity.bill;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class InventoryCheckHeader extends Header {
    @Column(nullable = false)
    private boolean executed = false;//是否执行

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<InventoryCheckDetail> details = new LinkedHashSet<>();//单据明细

    public InventoryCheckHeader() {
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    @Override
    public Set<InventoryCheckDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<InventoryCheckDetail> details) {
        this.details = details;
    }
}
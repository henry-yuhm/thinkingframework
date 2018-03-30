package org.jointown.logistics.core.entity.bill;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class ReplenishmentHeader extends Header {
    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "header_id")}, inverseJoinColumns = {@JoinColumn(name = "detail_id")})
    private Set<ReplenishmentDetail> details;//单据明细

    public ReplenishmentHeader() {
    }

    @Override
    public Set<ReplenishmentDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<ReplenishmentDetail> details) {
        this.details = details;
    }
}
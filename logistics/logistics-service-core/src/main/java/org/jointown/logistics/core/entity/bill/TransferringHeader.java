package org.jointown.logistics.core.entity.bill;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class TransferringHeader extends Header {
    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "header_id")}, inverseJoinColumns = {@JoinColumn(name = "detail_id")})
    private Set<TransferringDetail> details;//单据明细

    public TransferringHeader() {
    }

    @Override
    public Set<TransferringDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<TransferringDetail> details) {
        this.details = details;
    }
}
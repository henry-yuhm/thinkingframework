package org.jointown.logistics.core.entity.bill;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class SaleInboundHeader extends InboundHeader {

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "header_id")}, inverseJoinColumns = {@JoinColumn(name = "detail_id")})
    private Set<SaleInboundDetail> details;//单据明细

    @Override
    public Set<? extends Detail> getDetails() {
        return details;
    }
}
package org.jointown.logistics.core.entity.bill;

import org.jointown.logistics.core.entity.support.ArrivalVoucher;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class PurchaseInboundHeader extends InboundHeader {
    private ArrivalVoucher arrivalVoucher;//到货凭证

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "header_id")}, inverseJoinColumns = {@JoinColumn(name = "detail_id")})
    private Set<PurchaseInboundDetail> details;//单据明细

    public Set<? extends Detail> getDetails() {
        return details;
    }
}
package org.jointown.logistics.core.entity.bill;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class TransferringHeader extends Header {
    private String auditor;//审核员

    private String cancellingman;//取消人

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<TransferringDetail> details = new LinkedHashSet<>();//单据明细

    public TransferringHeader() {
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getCancellingman() {
        return cancellingman;
    }

    public void setCancellingman(String cancellingman) {
        this.cancellingman = cancellingman;
    }

    @Override
    public Set<TransferringDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<TransferringDetail> details) {
        this.details = details;
    }
}
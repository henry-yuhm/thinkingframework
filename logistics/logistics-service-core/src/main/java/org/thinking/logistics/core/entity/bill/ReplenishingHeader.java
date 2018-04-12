package org.thinking.logistics.core.entity.bill;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ReplenishingHeader extends Header {
    @Column(nullable = false)
    private boolean complete = false;//完成

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<ReplenishingDetail> details = new LinkedHashSet<>();//单据明细
}
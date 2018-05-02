package org.thinking.logistics.services.core.domain.bill;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class InventoryCheckHeader extends Header {
    @Column(nullable = false)
    private boolean executed = false;//执行

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<InventoryCheckDetail> details = new LinkedHashSet<>();//单据明细
}
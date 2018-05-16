package org.thinking.logistics.services.core.domain.documents;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ReplenishingDocumentsHeader extends Header {
    @Column(nullable = false)
    private boolean complete = false;//完成

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<ReplenishingDocumentsDetail> details = new LinkedHashSet<>();//单据明细
}
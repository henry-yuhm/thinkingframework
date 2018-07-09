package org.thinking.logistics.services.core.domain.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class CheckListHeader extends Header {
    @Column(nullable = false)
    private boolean executed = false;//执行

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<CheckListDetail> details;//单据明细
}
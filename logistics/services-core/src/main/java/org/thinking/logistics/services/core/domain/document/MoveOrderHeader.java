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
public class MoveOrderHeader extends Header {
    private String auditor;//审核员

    private String cancellingman;//取消人

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<MoveOrderDetail> details;//单据明细
}
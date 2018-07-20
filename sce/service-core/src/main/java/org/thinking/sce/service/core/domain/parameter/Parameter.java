package org.thinking.sce.service.core.domain.parameter;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.BaseDomainEntity;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Parameter extends BaseDomainEntity {
    @Column(unique = true, nullable = false, length = 100)
    private String name;//名称

    @Column(nullable = false)
    private String value;//值

    @Column(nullable = false)
    private String sign;//标识

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "parameter_id"), inverseJoinColumns = @JoinColumn(name = "range_id"))
    private Set<ParameterRange> ranges;//值域

    private String remarks;//备注
}
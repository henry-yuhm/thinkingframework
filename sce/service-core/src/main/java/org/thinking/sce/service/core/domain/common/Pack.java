package org.thinking.sce.service.core.domain.common;

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
public class Pack extends BaseDomainEntity {
    @Column(unique = true, nullable = false, length = 50)
    private String no;//编号

    @Column(length = 200)
    private String description;//描述

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "pack_id"), inverseJoinColumns = @JoinColumn(name = "component_id"))
    private Set<PackComponent> components;//包装成分
}